<template>
  <div class="list">
    <!--<li>{{cryptoCurrencies}}</li>-->
    <h1>Geladene Kryptowährungen</h1>
    <b-list-group class="list-group">
      <b-list-group-item v-for="cryptoCurrency in cryptoCurrencies" v-bind:key="cryptoCurrency.id">{{cryptoCurrency.name}}</b-list-group-item>
    </b-list-group>
  </div>
</template>

<script>
import axios from "axios"
import config from "@/config"

export default {
  data(){
    return {
      cryptoCurrencies: []
    }
  },
  methods: {
    loadCryptoCurrency: function(){
        axios.get(`${config.apiBaseUrl}/cryptos/list`).then((response) => {
            this.cryptoCurrencies = response.data;
        })
    },
  },
  created: function(){
        this.loadCryptoCurrency();
  }
}
</script>

<style scoped>
.list-group{
  max-width: 600px;
  max-height: 400px;
  margin-bottom: 10px;
  overflow:scroll;
  -webkit-overflow-scrolling: touch;
}
</style>
