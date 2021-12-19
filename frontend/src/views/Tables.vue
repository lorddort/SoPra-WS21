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
        taggedValue: [{ id: "bitcoin", name: "Bitcoin"}, { id: "ethereum", name : "Ethereum"}],
        cryptoCurrencies: [
                { id: "bitcoin", name: "Bitcoin"},
                { id: "ethereum", name : "Ethereum"},
                { id: "binancecoin", name: "Binance Coin"},
                { id: "tether", name: "Tether"},
                { id: "solana", name: "Solana"},
                { id: "usd-coin", name: "USD Coin"},
                { id: "cardano", name: "Cardano"},
                { id: "ripple", name: "XRP"},
                { id: "polkadot", name: "Polkadot"},
                { id: "terra-luna", name: "Terra"}
        ],
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
    searchTag ({ name }) {
        return `${name}`
    }
  }
}

</script>

<style src="vue-multiselect/dist/vue-multiselect.min.css"></style>