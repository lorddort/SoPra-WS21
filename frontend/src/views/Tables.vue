<template>
    <div>
        <b-row>
            <b-col >
                <b-container>
                    <label class="typo__label">From</label>
                    <b-form-datepicker v-model="dayOne" :max="maxDate" locale="en"></b-form-datepicker><br>
                    <label class="typo__label">till</label>
                    <b-form-datepicker v-model="dayTwo" :min="dayOne" :max="maxDate" locale="en"></b-form-datepicker><br>
                    <label class="typo__label">Cryptocurrencies</label>
                    <multiselect    v-model="taggedValue"
                                    label="name" 
                                    track-by="name" 
                                    :options="cryptoCurrencies" 
                                    :multiple="true" 
                                    :taggable="false" 
                                    @tag="searchTag">
                    </multiselect><br>
                    <b-form-group   label="Currency"
                                    v-slot="{ ariaDescribedby }">
                        <b-form-radio-group id="btn-radios-2"
                                            v-model="selectedCurrencies"
                                            :options="currencies"
                                            :aria-describedby="ariaDescribedby"
                                            button-variant="outline-primary"
                                            name="radio-btn-outline"
                                            buttons>
                        </b-form-radio-group>
                    </b-form-group><br>
                    <label for="sb-inline">Threshold</label>
                    <b-row align-v="start">
                        <b-col>
                            <b-form-spinbutton id="sb-inline" v-model="threshold" placeholder="--" min="0" max="1" step="0.05" inline></b-form-spinbutton>
                        </b-col>
                        <b-col cols="8">
                            <b-form-input v-model="threshold" placeholder="Enter threshold <= 1"></b-form-input>
                        </b-col>
                    </b-row>
                </b-container>
            </b-col>
            <b-col cols="9">
                <Table :taggedValue="this.taggedValue" />
            </b-col>
        </b-row>
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
    const now = new Date()
    const maxDate = new Date(now)
    return {
        taggedValue: [/*{ id: "bitcoin", name: "Bitcoin"}, { id: "ethereum", name : "Ethereum"}*/],
        cryptoCurrencies: [],
        selectedCurrencies: "EUR",
        currencies:[
            { text: "Euro", value: "EUR" },
            { text: "US Dollar", value: "USD" }
        ],
        dayOne: null,
        dayTwo: null,
        minDate: this.dayOne,
        maxDate: maxDate,
        threshold: null
    }
  },
  methods: {
    loadCryptoCurrency: function(){
        axios.get(`${config.apiBaseUrl}/cryptos/list/${10}`).then((response) => {
            this.cryptoCurrencies = response.data;
            if(this.cryptoCurrencies.length > 2){
                for(var i = 0; i < 2; i++){
                    this.taggedValue.push(this.cryptoCurrencies[i])
                }
            }
            console.log(this.cryptoCurrencies)
        })
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

<style src="vue-multiselect/dist/vue-multiselect.min.css"></style>