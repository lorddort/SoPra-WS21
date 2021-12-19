<template>
    <div id="chart" v-if="chartHasData">
      <apexcharts type="heatmap" height="350" :options="chartOptions" :series="series"></apexcharts>
      <button type="button" class="btn btn-primary" @click="updateMap()">Update Table</button>
    </div>
    <div v-else>
      <h1>No data to show</h1>
      <button type="button" class="btn btn-primary" @click="updateMap()">Update Table</button>
    </div>
</template>

<script>
import VueApexCharts from 'vue-apexcharts'
import axios from 'axios';
import config from "@/config"

export default {
    name: 'HeatMap',
    components: {
        apexcharts: VueApexCharts
    },
    props: { taggedValue: Array },
    data: function() {
        return{
          data: [],
          series: [],
          getnumber: 0.0,
          correlationData: [],
          chartHasData: true,
          chartOptions: {
              chart: {
                height: 350,
                type: 'heatmap',
              },
              plotOptions: {
                heatmap: {
                  shadeIntensity: 0.5,
                  radius: 0,
                  useFillColorAsStroke: true,
                  colorScale: {
                    ranges: [{
                        from: 0,
                        to: 0.33,
                        name: 'low',
                        color: '#00A100'
                        },
                        {
                        from: 0.34,
                        to: 0.66,
                        name: 'high',
                        color: '#FFB200'
                        },
                        {
                        from: 0.67,
                        to: 1,
                        name: 'extreme',
                        color: '#FF0000'
                        }]
                    }
                  }
                },
              dataLabels: {
                enabled: false
              },
              stroke: {
                width: 1
              },
              title: {
                text: 'Korrelation Table'
              },
          },
        }
    },
    /*watch: {
      taggedValue: function(){
        this.updateMap();
      }
    },*/
    methods: {
        loadCorrelation: function(type, id1, id2){
            const params = new URLSearchParams([['type', type], ['id1', id1], ['id2', id2]])
            axios.get(`${config.apiBaseUrl}/calculator/crypto/corr/year`, { params }).then((response) => {
                console.log(typeof response.data)
                this.getnumber = response.data
            })
            return this.getnumber
        },
        updateMap() {
          var series = [];
          for(var i = 0; i < this.taggedValue.length; i++){
            var dataArray = [];
            for(let j = 0; j < this.taggedValue.length; j++){
              dataArray.push({
                x: this.taggedValue[j].name,
                y: Math.random() //this.loadCorrelation("price", this.taggedValue[i].id, this.taggedValue[j].id)    //TODO get Korrelation from backend(promise)
              })
            }
            //console.log(dataArray)
            series.push({
              name: this.taggedValue[i].name,
              data: dataArray
            });
          }
          if(series.length == 0){
            this.chartHasData = false;
          } else {
            this.chartHasData = true;
          }
          this.series = series;
        }
    }
}
</script>