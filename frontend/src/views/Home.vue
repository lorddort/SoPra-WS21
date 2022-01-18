<template>
  <div>
    <h1>Geladene Kryptowährungen</h1>
    <div class="list">
      <b-list-group class="list-group" v-if="listHasData">
        <b-list-group-item v-for="cryptoCurrency in cryptoCurrencies" v-bind:key="cryptoCurrency.id">{{cryptoCurrency.name}}</b-list-group-item>
      </b-list-group>
      <b-list-group class="list-group" v-else>
        <b-spinner label="Loading..."></b-spinner>
      </b-list-group>
    </div>
  </div>
</template>

<script>
import axios from "axios"
import config from "@/config"

export default {
  data(){
    return {
      listHasData: false,
      cryptoCurrencies: []
    }
  },
  methods: {
    loadCryptoCurrency: function(){
        axios.get(`${config.apiBaseUrl}/cryptos/list`).then((response) => {
            this.cryptoCurrencies = response.data;
            if(this.cryptoCurrencies.length != 0){
              this.listHasData = true;
            }
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
