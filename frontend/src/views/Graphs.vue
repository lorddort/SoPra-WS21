<template>
    <div>
        <b-row>
            <b-col cols="2" >
                <b-container>
                    <b-button v-b-modal.timeFrameModal title="Time Frame" style="min-width: 100%">Time Frame: {{this.timeFrameString}}</b-button>
                    <br>
                    <label class="typo_label">Crypto Currencies</label>
                    <multiselect :options="currencyMap"
                        v-model="multiselectMap"
                        :multiple="true" 
                        :taggable="false"
                        label="name"
                        track-by="name">
                    </multiselect>
                </b-container>
            </b-col>
            <b-col cols="8">
                <GraphCard
                    :rawData="chartData"
                    :timeFrame="timeFrame"
                />
            </b-col>
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
import Multiselect from "vue-multiselect"
import config from "@/config";
import axios from "axios";

export default {
    name: "Graphs",
    components: {
        GraphCard,
        Multiselect
    },
    data() {
        return {
            currencyMap: [],
            rawData: [],
            selection: [],
            multiselectMap: [],
            chartData: [],
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
        },
        multiselectMap: function() {
            // add data to graph if new in selection
            for (let i in this.multiselectMap){
                if (!this.chartData.some(obj => obj.id == this.multiselectMap[i].id)){
                    this.loadRawData(this.multiselectMap[i].id);
                }
            }

            // remove data from graph if removed from selection
            for (let i in this.chartData){
                if (!this.multiselectMap.some(obj => obj.id == this.chartData[i].id)){
                    this.removeRawData(this.chartData[i].id);
                }
            }
        }
    },
    methods: {
        loadCurrencyMap: function(){
            axios.get(`${config.apiBaseUrl}/cryptos/list/${10}`).then((response) => {
                this.currencyMap = response.data;
            })
        },
        async loadRawData(id){
            if (this.chartData.some(curr => curr.id == id))return;
            
            await axios.post(`${config.apiBaseUrl}/cryptos/${id}`).then((response) => {
                this.chartData.push(response.data);
            });
        },
        removeRawData: function(id){
            let index;
            for (index = 0; index < this.chartData.length; index++){
                if (this.chartData[index].id == id){
                    break;
                }
            }
            this.chartData.splice(index, 1);
        },
        setTimeFrame: function(frame){
            let newFrame = {
                to: Math.floor(Date.now()),
                from: 0,
                frameType: 0
            }
            let now = newFrame.to;
            switch (frame){
                case this.frames.day:
                    newFrame.from = now - this.toUnixTime(1, 0, 0, 0);
                    newFrame.frameType = this.frames.day;
                    this.timeFrameString = "Last Day";
                    break;
                case this.frames.week: 
                    newFrame.from = now - this.toUnixTime(7, 0, 0, 0);
                    newFrame.frameType = this.frames.week;
                    this.timeFrameString = "Last Week";
                    break;
                case this.frames.month:
                    newFrame.from = now - this.toUnixTime(30, 0, 0, 0);
                    newFrame.frameType = this.frames.month;
                    this.timeFrameString = "Last Month";
                    break;
                case this.frames.year:
                    newFrame.from = now - this.toUnixTime(360, 0, 0, 0);
                    newFrame.frameType = this.frames.year;
                    this.timeFrameString = "Last Year";
                    break;
                case this.frames.custom:
                    // TODO
                    this.timeFrame.frameType = this.frames.custom;
                    break;
            }
            this.timeFrame = newFrame;
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
        this.multiselectMap.push({id:"bitcoin", name:"Bitcoin"});
        this.multiselectMap.push({id:"ethereum", name:"Ethereum"});
    }
}

</script>

<style src="vue-multiselect/dist/vue-multiselect.min.css">
.fixedDiv {
    border: 1px solid darkgray;
}

.overview {
    overflow: scroll;
    height: 200px;
}
</style>
