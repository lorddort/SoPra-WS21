<template>
  <div>
    <h1>Geladene Kryptowährungen</h1>
    <br />
    
    <div>
      <b-row>
        <b-card-group class="col-md-2" deck v-for="cryptoCurrency in loadedCryptoCurrencies" v-bind:key="cryptoCurrency.id">
          <b-card :title="cryptoCurrency.name" :img-src="cryptoCurrency.logo" img-alt="Image" img-top></b-card>
        </b-card-group>
      </b-row>
    </div>
    <br />
    <div class="list">
      <b-list-group class="list-group" v-if="listHasData">
        <b-list-group-item v-for="cryptoCurrency in cryptoCurrencies" v-bind:key="cryptoCurrency.id">
          {{cryptoCurrency.name}}
          <!--<b-button variant="primary"><b-icon class="buttons" icon="info"></b-icon></b-button>-->
        </b-list-group-item>
      </b-list-group>
      <b-list-group class="text-center" v-else>
        <b-spinner variant="primary" label="Text Centered"></b-spinner>
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
      cryptoCurrencies: [],
      loadedCryptoCurrencies: []
    }
  },
  methods: {
    async postCCForCorrelation(array){
        for(var i = 0; i < array.length; i++){
            await axios.post(`${config.apiBaseUrl}/cryptos/${array[i].id}`)
            this.loadedCryptoCurrencies.push({
              id: this.cryptoCurrencies[i].id,
              name: this.cryptoCurrencies[i].name,
              logo: await this.getLogo(array[i].id)
            })
        }
        console.log(this.loadedCryptoCurrencies)
    },
    loadCryptoCurrency(){
      axios.get(`${config.apiBaseUrl}/cryptos/list/${10}`).then((response) => {
        this.cryptoCurrencies = response.data;
        if(this.cryptoCurrencies.length != 0){
          this.listHasData = true;
        }
        this.postCCForCorrelation(this.cryptoCurrencies)
      })
    },
    getLogo: function(id){
      return axios.get(`${config.apiBaseUrl}/cryptos/${id}/logourl`).then((response) => {
        return response.data
      })
    }
  },
  created: function(){
        this.loadCryptoCurrency();
  }
}
</script>

<style scoped>
.list-group{
  max-width: 700px;
  max-height: 400px;
  margin-bottom: 10px;
  margin-left: auto;
  margin-right: auto;
  overflow:scroll;
  -webkit-overflow-scrolling: touch;
}

.text-center{
  text-align: center;
}

.buttons{
  margin: 2px 1px;
}
</style>
