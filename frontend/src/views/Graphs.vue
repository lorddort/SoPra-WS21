<template>
    <div>
        <b-row>
            <b-col cols="2" >
                <b-container>
                    <b-dropdown block size="md" id="graph-settings" text="Settings" class="pt-1" menu-class="w-100">
                        <b-dropdown-item v-b-modal.timeFrameModal title="Time Frame">Time Frame: {{this.timeFrameString}}</b-dropdown-item>
                        <!--<b-dropdown-item>Currency</b-dropdown-item>TODO whats that? -->
                    </b-dropdown>
                    <b-dropdown block id="Courses" text="Courses" class="py-1" menu-class="w-100">
                        <b-dropdown-item 
                            v-for="curr in currencyMap" :key="curr.id"
                            @click="addAndUpdate(curr.id)"    
                            >{{curr.name}}
                        </b-dropdown-item>
                    </b-dropdown>
                    <b-dropdown :text="this.selectedCurrency" class="mb-1 w-100">
                        <b-dropdown-item @click="setCurrency(currency.EUR)">EUR</b-dropdown-item>
                        <b-dropdown-item disabled @click="setCurrency(currency.USD)">USD</b-dropdown-item>
                    </b-dropdown>
                </b-container>
            </b-col>
            <b-col cols="8">
                <GraphCard
                    :rawData="this.chartData"
                    :timeFrame="this.timeFrame"
                />
            </b-col>
            <!--<b-col>
                <div>
                    <b-card v-for="n in 4" :key="n" style="min-height: 150px">
                        Graph Thumbnail TODO
                    </b-card>
                </div>
            </b-col>-->
        </b-row>
        <b-modal id="timeFrameModal" title="Select Timeframe">
            <b-dropdown id="timeFrame" :text="this.timeFrameString" style="min-width: 100%" menu-class="w-100"
                @ok="this.updateChart()">
                <b-dropdown-item @click="setTimeFrame(frames.day)">Last Day</b-dropdown-item>
                <b-dropdown-item @click="setTimeFrame(frames.week)">Last Week</b-dropdown-item>
                <b-dropdown-item @click="setTimeFrame(frames.month)">Last Month</b-dropdown-item>
                <b-dropdown-item @click="setTimeFrame(frames.year)">Last Year</b-dropdown-item>
                <b-dropdown-item disabled v-b-modal.customTimeFrameModal>Custom</b-dropdown-item>
            </b-dropdown>
        </b-modal>
        <b-modal id="customTimeFrameModal" title="Select Time Frame">
            <b-form inline>
                <!--TODO-->
                <b-form-datepicker id="customTimeFrameDatePickerFrom" v-model="customTimeFrameFrom">
                </b-form-datepicker>
                <b-form-datepicker id="customTimeFrameDatePickerTo" v-model="customTimeFrameTo">
                </b-form-datepicker>
            </b-form>
        </b-modal>
    </div>
    
</template>

<script>
import GraphCard from "@/components/GraphCard.vue";
import config from "@/config";
import axios from "axios";

export default {
    name: "Graphs",
    components: {
        GraphCard
    },
    data() {
        return {
            currencyMap: [],
            rawData: [],
            selection: [],
            chartData: [],
            selectedTimeFrame: {
                from: 0,
                to: 0
            },
            frames: {
                day: 0,
                week: 1,
                month: 2,
                year: 3,
                custom: 4
            },
            timeFrame: {
                frameType: 0,
                from: 0,
                to: 0
            },
            timeFrameString: "Last Day",
            customTimeFrameFrom: 0,
            customTimeFrameTo: 0,
            currency: {
                EUR: "EUR",
                USD: "USD"
            },
            selectedCurrency: this.currency
        }
    },
    watch: {
        selectedCurrency: function(){
            this.calculateExchange();
        }
    },
    methods: {
        loadCurrencyMap: function(){
            axios.get(`${config.apiBaseUrl}/cryptos/list/${10}`).then((response) => {
                this.currencyMap = response.data;
            })
        },
        async addToSelection(id){
            if (this.chartData.some(curr => curr.id == id))return;
            
            await axios.post(`${config.apiBaseUrl}/cryptos/${id}`).then((response) => {
                this.chartData.push(response.data);
            });
        },
        updateChart: function(){
            for (let i in this.selection){
                if (!this.chartData.some(obj => obj.id == this.selection[i].id)){
                    this.chartData.push(this.selection[i]);
                }
            }
        },
        addAndUpdate: function(id){
            this.addToSelection(id);
            this.updateChart();
        },
        setTimeFrame: function(frame){
            this.timeFrame.to = Math.floor(Date.now());
            let now = this.selectedTimeFrame.to;
            switch (frame){
                case this.frames.day:
                    this.timeFrame.from = now - this.toUnixTime(1, 0, 0, 0);
                    this.timeFrame.frameType = this.frames.day;
                    this.timeFrameString = "Last Day";
                    break;
                case this.frames.week: 
                    this.timeFrame.from = now - this.toUnixTime(7, 0, 0, 0);
                    this.timeFrame.frameType = this.frames.week;
                    this.timeFrameString = "Last Week";
                    break;
                case this.frames.month:
                    this.timeFrame.from = now - this.toUnixTime(30, 0, 0, 0);
                    this.timeFrame.frameType = this.frames.month;
                    this.timeFrameString = "Last Month";
                    break;
                case this.frames.year:
                    this.timeFrame.from = now - this.toUnixTime(360, 0, 0, 0);
                    this.timeFrame.frameType = this.frames.year;
                    this.timeFrameString = "Last Year";
                    break;
                case this.frames.custom:
                    // TODO
                    this.timeFrame.frameType = this.frames.custom;
                    break;
            }
            console.log(this.selectedTimeFrame);
        },
        //converts human readable time to unixTime
        toUnixTime: function(days, hours, minutes, seconds){
            return  days * 24 * 60 * 60 * 1000 +
                hours * 60 * 60 * 1000+
                minutes * 60 * 1000+
                seconds;
        },
        setCurrency: function(currency){
            this.selectedCurrency = currency;
        },
        //calculate exchange course
        calculateExchange: function(){
            //TODO
        }
    },
    created: function(){
        this.setTimeFrame(this.frames.day);
        this.selectedCurrency = this.currency.EUR;
        this.loadCurrencyMap();
        this.addToSelection("bitcoin");
        this.addToSelection("ethereum");
        this.updateChart();
    }
}

</script>

<style>
.fixedDiv {
    border: 1px solid darkgray;
}

.overview {
    overflow: scroll;
    height: 200px;
}
</style>