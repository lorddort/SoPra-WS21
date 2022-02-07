<template>
    <div class="layout">
        <h1>Calculate Correlation</h1>
        <b-row>
            <b-col >
                <b-container>
                    <label class="typo_label">Time Frame</label>
                    <b-button 
                        v-b-modal.timeFrameModal 
                        style="min-width: 100%"
                        variant="outline-dark">
                        {{this.timeFrameString}}
                    </b-button>
                    <br><br>
                    <label class="typo__label">Cryptocurrencies</label>
                    <multiselect    class="multiselect"
                                    v-model="taggedValue"
                                    label="name" 
                                    track-by="name" 
                                    :options="cryptoCurrencies" 
                                    :multiple="true" 
                                    :taggable="false" 
                                    @tag="searchTag">
                    </multiselect>
                    <br><br>
                    <b-button 
                        v-b-modal.typeModal 
                        style="min-width: 100%"
                        variant="outline-dark">
                        {{this.typeString}}
                    </b-button>
                    <br><br>
                    <label for="sb-inline">Threshold</label>
                    <br>
                    <b-form-spinbutton id="sb-inline" style="min-width: 100%" v-model="threshold" placeholder="--" min="-1" max="1" step="0.05" inline>{{threshold}}</b-form-spinbutton>
                    <b-button class="button button1" @click="startThreshold()">Apply Threshold</b-button>
                    <b-button class="button button2" @click="resetThreshold()">Reset Threshold</b-button>
                </b-container>
            </b-col>
            <b-col cols="9">
                <Table :taggedValue="this.taggedValue" :threshold="this.threshold" :appliedThreshold="this.appliedThreshold" :selectedTime="this.selectedTime" :selectedType="this.selectedType" />
            </b-col>
        </b-row>
        <b-modal id="timeFrameModal" title="Select Timeframe" hide-footer>
            <b-dropdown id="timeFrame" :text="this.timeFrameString" style="min-width: 100%" menu-class="w-100">
                <b-dropdown-item @click="setTimeFrame(frames.today)">Today</b-dropdown-item>
                <b-dropdown-item @click="setTimeFrame(frames.day)">24h</b-dropdown-item>
                <b-dropdown-item @click="setTimeFrame(frames.week)">Week</b-dropdown-item>
                <b-dropdown-item @click="setTimeFrame(frames.month)">Month</b-dropdown-item>
                <b-dropdown-item @click="setTimeFrame(frames.lastMonth)">Last Month</b-dropdown-item>
            </b-dropdown>
        </b-modal>
        <b-modal id="typeModal" title="Select Type" hide-footer>
            <b-dropdown id="type" :text="this.typeString" style="min-width: 100%" menu-class="w-100">
                <b-dropdown-item @click="setType(types.price)">Price</b-dropdown-item>
                <b-dropdown-item @click="setType(types.marketcap)">Marketcap</b-dropdown-item>
                <b-dropdown-item @click="setType(types.volume)">Volume</b-dropdown-item>
            </b-dropdown>
        </b-modal>
    </div>
</template>

<script>
import Table from "@/components/Table.vue"
import Multiselect from 'vue-multiselect'
import axios from "axios"
import config from "@/config"

export default {
    name: "Tables",
    components: {
        Table,
        Multiselect,
    },
  data () {
    return {
        selectedTime: "today",
        selectedType: "price",
        types: {
            marketcap: 0,
            volume: 1,
            correlation: 2
        },
        typeString: "Price",
        frames: {
            today: 0,
            day: 1,
            week: 2,
            month: 3,
            lastMonth: 4,
        },
        timeFrameString: "Today",
        taggedValue: [/*{ id: "bitcoin", name: "Bitcoin"}...*/],
        cryptoCurrencies: [],
        selectedCurrencies: "EUR",
        currencies:[
            { text: "Euro", value: "EUR" },
            { text: "US Dollar", value: "USD" }
        ],
        threshold: 0,
        appliedThreshold: false,
    }
  },
  methods: {
    /*postCCForCorrelation: function(array){
        for(var i = 0; i < array.length; i++){
            axios.post(`${config.apiBaseUrl}/cryptos/${array[i].id}`)
        }
    },
    loadCryptoCurrency: function(){
        axios.get(`${config.apiBaseUrl}/cryptos/list/${10}`).then((response) => {
            this.cryptoCurrencies = response.data;
            if(this.cryptoCurrencies.length > 2){
                for(var i = 0; i < 2; i++){
                    this.taggedValue.push(this.cryptoCurrencies[i])
                }
            }
            this.postCCForCorrelation(this.cryptoCurrencies)
        })
    },*/
    setTimeFrame: function(frame){
        switch (frame){
            case this.frames.today:
                this.selectedTime = "today"
                this.timeFrameString = "Today"
                break;
            case this.frames.day: 
                this.selectedTime = "24h"
                this.timeFrameString = "24h"
                break;
            case this.frames.week:
                this.selectedTime = "week"
                this.timeFrameString = "Week"
                break;
            case this.frames.month:
                this.selectedTime = "month"
                this.timeFrameString = "Month"
                break;
            case this.frames.lastMonth:
                this.selectedTime = "last_month"
                this.timeFrameString = "Last Month"
                break;
        }
    },
    setType: function(type){
        switch (type){
            case this.types.marketcap:
                this.selectedType = "marketcap"
                this.typeString = "Marketcap"
                break;
            case this.types.volume:
                this.selectedType = "volume"
                this.typeString = "Volume"
                break;
            case this.types.price:
                this.selectedType = "price"
                this.typeString = "Price"
                break;
        }
    },
    resetThreshold: function(){
        if(this.appliedThreshold == true){
            this.appliedThreshold = false;
            this.threshold = 0;
        }
    },
    startThreshold: function(){
        if(this.appliedThreshold == false){
            this.appliedThreshold = true;
        }
    },
    searchTag ({ name }) {
        return `${name}`
    },
    loadloadedCryptoCurrency: function(){
        axios.get(`${config.apiBaseUrl}/cryptos/loaded`).then((response) => {
            this.cryptoCurrencies = response.data;
            if(this.cryptoCurrencies.length > 2){
                for(var i = 0; i < 2; i++){
                    this.taggedValue.push(this.cryptoCurrencies[i])
                }
            }
        })
    }
  },
  created: function(){
        this.loadloadedCryptoCurrency();
  }
}

</script>

<style scoped>
.multiselect {
    @import "vue-multiselect/dist/vue-multiselect.min.css"
}
.button {
  border: none;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
}
.button1 {padding: 7px 20px; background-color: rgb(39, 138, 64);}
.button2 {padding: 7px 20px; background-color: #ac2e2e;}

.layout{
    width: 1750px;
    margin: auto;
}
</style>