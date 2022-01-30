<template>
    <div>
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
                    <label for="sb-inline">Threshold</label>
                    <br>
                    <b-form-spinbutton id="sb-inline" style="min-width: 100%" v-model="threshold" placeholder="--" min="-1" max="1" step="0.05" inline>{{threshold}}</b-form-spinbutton>
                    <b-button class="button button1" @click="startThreshold()">Apply Threshold</b-button>
                    <b-button class="button button2" @click="resetThreshold()">Reset Threshold</b-button>
                </b-container>
            </b-col>
            <b-col cols="9">
                <Table :taggedValue="this.taggedValue" :threshold="this.threshold" :appliedThreshold="this.appliedThreshold"/>
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
        frames: {
            day: 0,
            week: 1,
            month: 2,
            year: 3,
            custom: 4
        },
        timeFrameString: "Last Day",
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
    postCCForCorrelation: function(array){
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
    }
  },
  created: function(){
        this.loadCryptoCurrency();
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


</style>