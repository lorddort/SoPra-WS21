<template>
    <div>
        <b-row>
            <b-col >
                <b-container>
                    <label class="typo__label">Period</label><br>
                    <label class="typo__label">From</label>
                    <b-form-datepicker v-model="minValue" :min="min" locale="en"></b-form-datepicker><br>
                    <label class="typo__label">till</label>
                    <b-form-datepicker v-model="maxValue" :max="max" locale="en"></b-form-datepicker><br>
                    <label class="typo__label">Courses</label>
                    <multiselect    v-model="value" 
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
                                            v-model="selected"
                                            :options="currencies"
                                            :aria-describedby="ariaDescribedby"
                                            button-variant="outline-primary"
                                            name="radio-btn-outline"
                                            buttons>
                        </b-form-radio-group>
                    </b-form-group><br>
                </b-container>
            </b-col>
            <b-col cols="10">
                <Table />
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
    const today = new Date(now.getFullYear(), now.getMonth(), now.getDate())
    // 15th two months prior
    const minDate = new Date(now)
    //minDate.setMonth(minDate.getMonth() - 2)
    //minDate.setDate(15)
    // 15th in two months
    const maxDate = new Date(today)
    //maxDate.setMonth(maxDate.getMonth() + 2)
    //maxDate.setDate(15)

    return {
        value: null,
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
        selected: "EUR",
        currencies:[
            { text: "Euro", value: "EUR" },
            { text: "US Dollar", value: "USD" }
        ],
        minValue: '',
        maxValue: '',
        min: minDate,
        max: maxDate
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