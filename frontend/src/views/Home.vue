<template>
  <div class="layout">
    <h1>Preloading Top 10 Crypto Assets</h1>
    <br>
    <b-button class="button button1" v-b-modal.listModal>Add Crypto Assets</b-button>
    <button class="btn btn-primary" type="button" v-show="loading" disabled>
      <span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>
      Loading...
    </button>
    <br>
    <div>
      <b-row>
        <b-card-group class="col-md-2" deck v-for="cryptoCurrency in loadedCryptoCurrencies" v-bind:key="cryptoCurrency.id">
          <b-card :title="cryptoCurrency.name" :img-src="cryptoCurrency.logo" img-alt="Image" img-top><router-link class="button button2" v-bind:to="'/'+cryptoCurrency.id">Details</router-link></b-card>
        </b-card-group>
      </b-row>
    </div>
    <br>
    <div class="list">
      <b-modal id="listModal" title="Select Assets">
        <b-list-group class="list-group" v-if="listHasData">
          <b-list-group-item v-for="crypto in allCryptos" v-bind:key="crypto.id">
            {{crypto.name}}
            <b-button class="buttonlist" right disabled><b-icon class="buttons" icon="clipboard-plus"></b-icon></b-button>
          </b-list-group-item>
        </b-list-group>
        <b-list-group class="text-center" v-else>
          <b-spinner variant="primary" label="Text Centered"></b-spinner>
        </b-list-group>
      </b-modal>
    </div>
  </div>
</template>

<script>
import axios from "axios"
import config from "@/config"

export default {
  data(){
    return {
      loading: true,
      listHasData: false,
      hasCreateCC: false,
      cryptoCurrencies: [],
      loadedCryptoCurrencies: [],
      allCryptos: []
    }
  },
  methods: {
    createCC(){
      axios.get(`${config.apiBaseUrl}/cryptos/list/${10}`).then((response) => {
        this.cryptoCurrencies = response.data;
        this.addCC(this.cryptoCurrencies);
      })
    },
    getAllCC(){
      axios.get(`${config.apiBaseUrl}/cryptos/list/${100}`).then((response) => {
        this.allCryptos = response.data;
        if(this.allCryptos.length != 0){
          this.listHasData = true;
        }
      })
    },
    async addCC(array){
        for(var i = 0; i < array.length; i++){
            await axios.post(`${config.apiBaseUrl}/cryptos/${array[i].id}`)
            this.loadedCryptoCurrencies.push({
              id: this.cryptoCurrencies[i].id,
              name: this.cryptoCurrencies[i].name,
              logo: await this.getLogo(array[i].id)
            })
        }
        if(this.loadedCryptoCurrencies.length==10){
          this.loading=false;
        }
    },
    getLogo: function(id){
      return axios.get(`${config.apiBaseUrl}/cryptos/${id}/logourl`).then((response) => {
        return response.data
      })
    }/*,
    loadCC(){
      axios.get(`${config.apiBaseUrl}/cryptos/loaded`).then((response) => {
        this.cryptoCurrencies = response.data;
        this.addCC(this.cryptoCurrencies);
        if(response.data.length != 0){
          this.hasCreateCC = false;
        }
      })
    },
    async getCC(array){
        for(var i = 0; i < array.length; i++){
            await axios.get(`${config.apiBaseUrl}/cryptos/${array[i].id}`)
            this.loadedCryptoCurrencies.push({
              id: this.cryptoCurrencies[i].id,
              name: this.cryptoCurrencies[i].name,
              logo: await this.getLogo(array[i].id)
            })
        }
    }*/
  },
  created: function(){
    this.getAllCC();
    this.createCC();
  }
}
</script>

<style scoped>
.button {
  border: none;
  color: rgb(255, 255, 255);
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
}

.button1 {padding: 7px 20px; background-color: rgb(27, 180, 53);}
.button2 {padding: 7px 20px; background-color: #16a76f;}

.buttonlist {
  display: flex;
  justify-content: right;
  margin: 0 auto;
  color: #353636;
}

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

.layout{
  width: 1750px;
  margin: auto;
}
</style>
