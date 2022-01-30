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
                    <br>
                    <br>
                    <b-button 
                        style="min-width:100%"
                        variant="outline-dark"
                        @click="addGraph()"
                        :disabled="disableMoreGraphs">
                        Add Graph
                    </b-button>
                </b-container>
            </b-col>
            <b-col cols="8">
                <b-row v-for="line in graphCollection">
                    <b-card-group deck style="min-width: 100%">
                        <b-card v-for="graph in line" class="my-3">
                            <b-button
                                class="sm"
                                variant="danger"
                                :disabled="disableDeleteGraph"
                                @click="deleteGraph(graph.id)">
                                Delete Graph
                            </b-button>
                            <GraphCard :rawData="graph.chartData" :timeFrame="graph.timeFrame"/>
                        </b-card>
                    </b-card-group>
                </b-row>
            </b-col>
        </b-row>
        <b-modal id="timeFrameModal" title="Select Timeframe">
            <b-dropdown id="timeFrame" :text="this.timeFrameString" style="min-width: 100%" menu-class="w-100">
                <b-dropdown-item @click="setTimeFrame(frames.day)">Last Day</b-dropdown-item>
                <b-dropdown-item @click="setTimeFrame(frames.week)">Last Week</b-dropdown-item>
                <b-dropdown-item @click="setTimeFrame(frames.month)">Last Month</b-dropdown-item>
                <b-dropdown-item @click="setTimeFrame(frames.year)">Last Year</b-dropdown-item>
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
            usedGraphIds: new Map([
                [0, false],
                [1, false],
                [2, false],
                [3, false]
            ]),
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
            }
        }
    },
    computed: {
        disableMoreGraphs: function() {
            if (this.graphCollection.length == 2){
                if (this.graphCollection[0].length == 2 && this.graphCollection[1].length == 2){
                    return true;
                }
            }
            return false;
        },
        disableDeleteGraph: function() {
            if (this.graphCollection.length == 1 && this.graphCollection[0].length == 1){
                return true;
            }
            return false;
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
            this.updateGraphData(this.activeGraph);
        },
        removeRawData: function(id){
            let index;
            for (index = 0; index < this.chartData.length; index++){
                if (this.chartData[index].id == id){
                    break;
                }
            }
            this.chartData.splice(index, 1);
            this.updateGraphData(this.activeGraph);
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
            this.updateGraphData(this.activeGraph);
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
            for (let i = 0; i < this.graphCollection.length; i++){
                for(let j = 0; j < this.graphCollection[i].length; j++){
                    if (this.graphCollection[i][j].id == id){
                        this.graphCollection[i][j].chartData = JSON.parse(JSON.stringify(this.chartData));
                        this.graphCollection[i][j].timeFrame = JSON.parse(JSON.stringify(this.timeFrame));
                        return;
                    }
                }
            }
        },
        addGraph: function(){
            //update activeGraph value
            this.activeGraph = this.getNextGraphId();
            this.usedGraphIds.set(this.activeGraph, true);
            //create graph object
            const newGraph = {
                id: this.activeGraph,
                chartData: JSON.parse(JSON.stringify(this.chartData)),
                timeFrame: JSON.parse(JSON.stringify(this.timeFrame))
            }
            //queue old graphs and push new graph in first position
            let graphQueue = this.getGraphQueue();
            graphQueue.unshift(newGraph);
            this.graphCollection = JSON.parse(JSON.stringify(this.getGraphViewLayout(graphQueue)));
        },
        deleteGraph: function(id){
            this.usedGraphIds.set(id, false);
            let graphQueue = this.getGraphQueue();
            for (let i = 0; i < graphQueue.length; i++){
                if (graphQueue[i].id == id){
                    graphQueue.splice(i, 1);
                }
            }
            this.activeGraph = graphQueue[0].id;
            this.graphCollection = this.getGraphViewLayout(graphQueue);
        },
        getGraphQueue: function(){
            let graphQueue = [];
            for (let i = 0; i < this.graphCollection.length; i++){
                for (let j = 0; j < this.graphCollection[i].length; j++){
                    graphQueue.push(this.graphCollection[i][j]);
                }
            }
            return graphQueue;
        },
        getGraphViewLayout: function(graphQueue){
            let newGraphCollection = [];
            switch (graphQueue.length){
                case 1: newGraphCollection.push([graphQueue[0]]);
                    break;
                case 2: newGraphCollection.push([graphQueue[0]]);
                    newGraphCollection.push([graphQueue[1]]);
                    break;
                case 3: newGraphCollection.push([graphQueue[0]]);
                    newGraphCollection.push([graphQueue[1], graphQueue[2]]);
                    break;
                case 4: newGraphCollection.push([graphQueue[0], graphQueue[1]]);
                    newGraphCollection.push([graphQueue[2], graphQueue[3]]);
                    break;
                default:
                    break;
            }
            return newGraphCollection;
        },
        getNextGraphId: function() {
            for (const [key, value] of this.usedGraphIds){
                if (!value){
                    return key;
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
        this.graphCollection.push([{
            id: 0,
            chartData: JSON.parse(JSON.stringify(this.chartData)),
            timeFrame: JSON.parse(JSON.stringify(this.timeFrame))
        }]);
        this.usedGraphIds.set(0, true);
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
