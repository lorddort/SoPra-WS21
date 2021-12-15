<template>
    <div>
        <b-row>
            <b-col cols="2" class="fixedDiv h-100">
                <b-container>
                    <b-dropdown block size="md" id="graph-settings" text="Settings" class="pt-1" menu-class="w-100">
                        <b-dropdown-item v-b-modal.timeFrameModal title="Time Frame">Time Frame:{{this.timeFrame}}</b-dropdown-item>
                        <b-dropdown-item>Currency</b-dropdown-item>
                    </b-dropdown>
                    <b-dropdown block id="Courses" text="Courses" class="py-1" menu-class="w-100">
                        <b-dropdown-item 
                            v-for="curr in currencies" :key="curr.id"
                            @click="addToSelection(curr.id)"    
                            >{{curr.name}}
                        </b-dropdown-item>
                    </b-dropdown>
                    <b-button block class="mb-1" @click="loadData()">Load Data</b-button>
                </b-container>
            </b-col>
            <b-col cols="8">
                <GraphCard :currencies="this.selection"/>
            </b-col>
            <b-col>
                <div>
                    <b-card v-for="n in 4" :key="n" style="min-height: 150px">
                        Graph Thumbnail <!--TODO-->
                    </b-card>
                </div>
            </b-col>
        </b-row>
        <b-modal id="timeFrameModal" title="Select Timeframe">
            <b-dropdown id="timeFrame" :text="this.timeFrame" style="min-width: 100%" menu-class="w-100">
                <b-dropdown-item @click="setTimeFrame(frames.day)">Last Day</b-dropdown-item>
                <b-dropdown-item @click="setTimeFrame(frames.week)">Last Week</b-dropdown-item>
                <b-dropdown-item @click="setTimeFrame(frames.month)">Last Month</b-dropdown-item>
                <b-dropdown-item @click="setTimeFrame(frames.year)">Last Year</b-dropdown-item>
                <b-dropdown-item v-b-modal.customTimeFrameModal>Custom</b-dropdown-item> 
            </b-dropdown>
        </b-modal>
        <b-modal id="customTimeFrameModal" title="Select Time Frame">
            <b-form @submit="setTimeFrame(frames.custom)">
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

export default {
    name: "Graphs",
    components: {
        GraphCard
    },
    data() {
        return {
            currencies: [],
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
            timeFrame: "Select Time Frame"
        }
    },
    methods: {
        //load example data
        loadData: function() {
            var loaded = require('./../../demo_data/bitcoin-example.json');
            if (!this.currencies.some(c => c.id === loaded.id)){
                this.currencies.push(loaded);
            }
        },
        //add loaded data to graph selection
        addToSelection: function(id){
            //early escape if already in selection
            if (this.selection.some(curr => curr.id === id)) return;

            let currentTime = Math.floor(Date.now() / 1000);
            let xValues = [];
            let yValues = [];
            let selectedCurrency = [];
            
            for (let key in this.currencies){
                if (this.currencies[key].id === id){
                    selectedCurrency = this.currencies[key];
                    break;
                }
            }
            
            //TODO, fix below threshold of 20 seconds                              _here_
            if (this.selectedTimeFrame.from > currentTime - this.toUnixTime(1, 0, 0, 20)){
                for (let key in selectedCurrency.minutelyChart.prices){
                    let tuple = selectedCurrency.minutelyChart.prices[key];
                    xValues.push(tuple[0]);
                    yValues.push(tuple[1]);
                }
            } else if (this.selectedTimeFrame.from > currentTime - this.toUnixTime(30, 0, 0, 0)){
                for (let key in selectedCurrency.hourlyChart.prices){
                    let tuple = selectedCurrency.hourlyChart.prices[key];
                    xValues.push(tuple[0]);
                    yValues.push(tuple[1]);
                }
            } else {
                for (let key in selectedCurrency.dailyChart.prices){
                    let tuple = selectedCurrency.dailyChart.prices[key];
                    xValues.push(tuple[0]);
                    yValues.push(tuple[1]);
                }
            }
            
            this.selection.push({
                id: id,
                name: selectedCurrency.name,
                x: xValues,
                y: yValues
            });
        },
        setTimeFrame: function(frame){
            this.selectedTimeFrame.to = Math.floor(Date.now() / 1000);
            let now = this.selectedTimeFrame.to;
            switch (frame){
                case this.frames.day:
                    this.selectedTimeFrame.from = now - this.toUnixTime(1, 0, 0, 0);
                    this.timeFrame = "Last Day";
                    break;
                case this.frames.week: 
                    this.selectedTimeFrame.from = now - this.toUnixTime(7, 0, 0, 0);
                    this.timeFrame = "Last Week";
                    break;
                case this.frames.month:
                    this.selectedTimeFrame.from = now - this.toUnixTime(30, 0, 0, 0);
                    this.timeFrame = "Last Month";
                    break;
                case this.frames.year:
                    this.selectedTimeFrame.form = now - this.toUnixTime(360, 0, 0, 0);
                    this.timeFrame = "Last Year";
                    break;
                case this.frames.custom:
                    // TODO
                    this.timeFrame = "Custom"
                    break;
            }
        },
        //converts human readable time to unixTime
        toUnixTime: function(days, hours, minutes, seconds){
            return  days * 24 * 60 * 60 +
                hours * 60 * 60 +
                minutes * 60 +
                seconds;
        }
    },
    created: function(){
        // Set default time frame to last 24 hours
        this.selectedTimeFrame.to = Math.floor(Date.now() / 1000);
        this.selectedTimeFrame.from = this.selectedTimeFrame.to - this.toUnixTime(1, 0, 0, 0);
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