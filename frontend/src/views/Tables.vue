<template>
    <div>
        <b-row>
            <b-col >
                <b-container>
                    <b-dropdown block size="md" id="graph-settings" text="Settings" class="pt-1">
                        <b-dropdown-item>Time Frame</b-dropdown-item>
                    </b-dropdown><br>
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
                    <b-button block>Export</b-button>
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
        Multiselect
    },
  data () {
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
        ]
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