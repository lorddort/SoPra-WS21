<template>
  <div id="chart" v-if="chartHasData">
    <apexcharts type="heatmap" height="350" :options="chartOptions" :series="series"></apexcharts>
  </div>
  <div v-else>
    <h1>No data to show</h1>
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
          correlation: [{ correlationCoefficient: null }],
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
                          to: 0.199999,
                          color: '#FBEFEF'
                        },
                        {
                          from: 0.2,
                          to: 0.399999,
                          color: '#F6CECE'
                        },
                        {
                          from: 0.4,
                          to: 0.599999,
                          color: '#F78181'
                        },
                        {
                          from: 0.6,
                          to: 0.799999,
                          color: '#FA5858'
                        },
                        {
                          from: 0.8,
                          to: 0.999999,
                          color: '#FF0000'
                        },
                        {
                          from: 1,
                          to: 1,
                          color: '#000000'
                        }]
                    }
                  }
                },
              dataLabels: {
                enabled: true
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
    watch: {
      taggedValue: function(){
        this.updateMap();
      }
    },
    methods: {
        loadCorrelation(type, id1, id2){
            const params = new URLSearchParams([['type', type], ['id1', id1], ['id2', id2]])
            return axios.get(`${config.apiBaseUrl}/calculator/crypto/corr/year`, { params }).then(response => {
              return response.data
            })
        },
        async updateMap() {
          var series = [];
          for(var i = 0; i < this.taggedValue.length; i++){
            var dataArray = [];
            for(let j = 0; j < this.taggedValue.length; j++){
              this.correlation = await this.loadCorrelation("price", this.taggedValue[i].id, this.taggedValue[j].id)
              dataArray.push({
                x: this.taggedValue[j].name,
                y: this.correlation.correlationCoefficient //TODO get Korrelation from backend(promise)
              })
            }
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