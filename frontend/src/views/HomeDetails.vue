<template>
    <div>
        <router-link to="/">Back</router-link>
        <b-media>
            <template #aside>
                <b-img :src="crypto.logoUrl" width="200"></b-img>
            </template>
            <b-jumbotron class="jumbotron" :header="crypto.name">
            </b-jumbotron>
        </b-media>
        <h1>Description</h1>
        <p>{{ crypto.description }}</p>
    </div>
</template>

<script>
import config from "@/config";
import axios from "axios";

export default {
    data(){
        return {
            crypto:[{
                id: null,
                name: null,
                description: null,
                logoUrl: null,

            }]
        }
    },
    methods: {
        fetchCrypto: function(id){
            axios.get(`${config.apiBaseUrl}/cryptos/${id}`).then((response) => {
                this.crypto = response.data;
                console.log(response.data);
            })
        }
    },
    created: function(){
        this.fetchCrypto(this.$route.params.id)
    }
}
</script>

<style>
</style>