<template>
    <div>
        <b-row>
            <b-col cols="2" >
                <b-container>
                    <label class="typo_label">Time Frame</label>
                    <b-button 
                        v-b-modal.timeFrameModal 
                        style="min-width: 100%"
                        variant="outline-dark">
                        {{this.timeFrameString}}
                    </b-button>
                    <br>
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
                <b-row v-for="row in graphCollection" id: row>
                    <b-col v-for="graph in row" id:graph.id>
                        <GraphCard :rawData="graph.rawData" :timeFrame="graph.timeFrame"/>
                    </b-col>
                </b-row>
                <!--GraphCard
                    :rawData="chartData"
                    :timeFrame="timeFrame"
                /-->
            </b-col>
        </b-row>
        <b-modal id="timeFrameModal" title="Select Timeframe">
            <b-dropdown id="timeFrame" :text="this.timeFrameString" style="min-width: 100%" menu-class="w-100">
                <b-dropdown-item @click="setTimeFrame(frames.day)">Last Day</b-dropdown-item>
                <b-dropdown-item @click="setTimeFrame(frames.week)">Last Week</b-dropdown-item>
                <b-dropdown-item @click="setTimeFrame(frames.month)">Last Month</b-dropdown-item>
                <b-dropdown-item @click="setTimeFrame(frames.year)">Last Year</b-dropdown-item>
                <!--<b-dropdown-item v-b-modal.customTimeFrameModal>Custom</b-dropdown-item>-->
            </b-dropdown>
        </b-modal>
    </div>
    
</template>

<script>
import GraphCard from "@/components/GraphCard.vue";
import Multiselect from "vue-multiselect"
import config from "@/config";
import axios from "axios";
import Vue from "vue";

//register graph card component
Vue.component("graph-card", GraphCard);

export default {
    name: "Graphs",
    components: {
        Multiselect,
        GraphCard
    },
    data() {
        return {
            activeGraph: 0,
            graphCollection: [],
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
            selectTimeFrameFrom: typeof(Date),
            selectTimeFrameTo: typeof(Date),
            selectDateMax: new Date(Date.now()),
            selectDateMin: new Date(Date.now() - this.toUnixTime(365, 0, 0, 0)),
            selectDateComputedMin: new Date(Date.now() - this.toUnixTime(365, 0, 0, 0)),
            selectDateComputedMax: new Date(Date.now()),
            currency: {
                EUR: "EUR",
                USD: "USD"
            },
        }
    },
    watch: {
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
        },
        selectTimeFrameFrom: function(){
            this.selectDateComputedMin = this.selectTimeFrameFrom;
        },
        selectTimeFrameTo: function(){
            this.selectDateComputedMax = this.selectTimeFrameTo;
        }
    },
    methods: {
        lockInCustomTimeFrame: function(){
            let newFrame = {
                to: 0,
                from: 0,
                frameType: this.frames.custom
            };
            newFrame.to = Math.floor(this.selectDateTo);
            newFrame.to = Math.floor(this.selectDateFrom);
            this.timeFrame = newFrame;
        },
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
                hours * 60 * 60 * 1000 +
                minutes * 60 * 1000 +
                seconds * 1000;
        },
        setCurrency: function(currency){
            this.selectedCurrency = currency;
        },
        updateGraphData: function(id) {
            for (let i = 0; i < this.graphCollection.lenght; i++){
                for (let j = 0; j < this.graphCollection[i].length; j++){
                    if (this.graphCollection[i][j].id == id){
                        

                        return;
                    }
                }
            }
        }
    },
    created: function(){
        this.setTimeFrame(this.frames.day);
        this.selectedCurrency = this.currency.EUR;
        this.loadCurrencyMap();
        this.multiselectMap.push({id:"bitcoin", name:"Bitcoin"});
        this.multiselectMap.push({id:"ethereum", name:"Ethereum"});
        this.updateActiveGraphData();
        this.graphCollection.push([{
            id: 0,
            chartData: this.chartData,
            timeFrame: this.timeFrame
        }]);
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
