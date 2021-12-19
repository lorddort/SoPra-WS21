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
                            @click="addToSelection(curr.id)"    
                            >{{curr.name}}
                        </b-dropdown-item>
                    </b-dropdown>
                    <b-dropdown :text="this.selectedCurrency" class="mb-1 w-100">
                        <b-dropdown-item @click="setCurrency(currency.EUR)">EUR</b-dropdown-item>
                        <b-dropdown-item disabled @click="setCurrency(currency.USD)">USD</b-dropdown-item>
                    </b-dropdown>
                    <b-button block class="mb-1" @click="loadCurrencyMap()">Get Available Currencies</b-button>
                </b-container>
            </b-col>
            <b-col cols="8">
                <GraphCard :currencies="this.selection"/>
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
            <b-dropdown id="timeFrame" :text="this.timeFrameString      " style="min-width: 100%" menu-class="w-100">
                <b-dropdown-item @click="setTimeFrame(frames.day)">Last Day</b-dropdown-item>
                <b-dropdown-item @click="setTimeFrame(frames.week)">Last Week</b-dropdown-item>
                <b-dropdown-item @click="setTimeFrame(frames.month)">Last Month</b-dropdown-item>
                <b-dropdown-item @click="setTimeFrame(frames.year)">Last Year</b-dropdown-item>
                <!--<b-dropdown-item v-b-modal.customTimeFrameModal>Custom</b-dropdown-item>-->
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
            selectedTimeFrame: {
                from: 0,
                to: 0
            },
            frames: {
                day: 1,
                week: 2,
                month: 3,
                year: 4,
                custom: 5
            },
            timeFrame: 1,
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
        //add loaded data to graph selection
        addToSelection: function(id){
            for (let i in this.selection){
                if (this.selection[i].id == id){
                    this.selection.splice(i, 1);
                }
            }

            let xValues = [];
            let yValues = [];
            let selectedCC;

            axios.post(`${config.apiBaseUrl}/cryptos/${id}`).then((response) => {
                selectedCC = response.data;

            if (this.timeFrame == this.frames.day){
                for (let key in selectedCC.minutelyChart.prices){
                    let tuple = selectedCC.minutelyChart.prices[key];
                    xValues.push(tuple[0]);
                    yValues.push(tuple[1]);
                }
            } else if (this.timeFrame == this.frames.week){
                for (let key in selectedCC.hourlyChart.prices){
                    let tuple = selectedCC.hourlyChart.prices[key];
                    if (this.selectedTimeFrame.from <= parseInt(tuple[0]) && parseInt(tuple[0]) <= this.selectedTimeFrame.to){
                        xValues.push(tuple[0]);
                        yValues.push(tuple[1]);
                    }
                }
            } else if (this.timeFrame == this.frames.month){
                for (let key in selectedCC.hourlyChart.prices){
                    let tuple = selectedCC.hourlyChart.prices[key];
                    xValues.push(tuple[0]);
                    yValues.push(tuple[1]);
                }
            } else {
                for (let key in selectedCC.dailyChart.prices){
                    let tuple = selectedCC.dailyChart.prices[key];
                    xValues.push(tuple[0]);
                    yValues.push(tuple[1]);
                }
            }
            
            this.selection.push({
                id: id,
                name: selectedCC.name,
                x: xValues,
                y: yValues
            });

            });
        },
        setTimeFrame: function(frame){
            this.selectedTimeFrame.to = Math.floor(Date.now());
            let now = this.selectedTimeFrame.to;
            switch (frame){
                case this.frames.day:
                    this.selectedTimeFrame.from = now - this.toUnixTime(1, 0, 0, 0);
                    this.timeFrame = this.frames.day;
                    this.timeFrameString = "Last Day";
                    break;
                case this.frames.week: 
                    this.selectedTimeFrame.from = now - this.toUnixTime(7, 0, 0, 0);
                    this.timeFrame = this.frames.week;
                    this.timeFrameString = "Last Week";
                    break;
                case this.frames.month:
                    this.selectedTimeFrame.from = now - this.toUnixTime(30, 0, 0, 0);
                    this.timeFrame = this.frames.month;
                    this.timeFrameString = "Last Month";
                    break;
                case this.frames.year:
                    this.selectedTimeFrame.from = now - this.toUnixTime(360, 0, 0, 0);
                    this.timeFrame = this.frames.year;
                    this.timeFrameString = "Last Year";
                    break;
                case this.frames.custom:
                    // TODO
                    this.timeFrame = this.frames.custom;
                    break;
            }
            console.log("from: " + this.selectedTimeFrame.from + "; to: " + this.selectedTimeFrame.to);

            for (let key in this.selection){
                this.addToSelection(this.selection[key].id);
            }
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