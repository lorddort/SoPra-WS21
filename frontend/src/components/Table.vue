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
    props: { taggedValue: Array, threshold: Number, appliedThreshold: Boolean},
    data: function() {
        return{
          thresholdLegend: [],
          defaultLegend: [{
                        from: -1,
                        to: -0.800001,
                        color: '#000000',
                        name: '>= -1'
                      },
                      {
                        from: -0.8,
                        to: -0.600001,
                        color: '#ab003d',
                        name: '>= -0.8'
                      },
                      {
                        from: -0.6,
                        to: -0.400001,
                        color: '#e52143',
                        name: '>= -0.6'
                      },
                      {
                        from: -0.4,
                        to: -0.200001,
                        color: '#ff6223',
                        name: '>= -0.4'
                      },
                      {
                        from: -0.2,
                        to: -0.000001,
                        color: '#ffad4a',
                        name: '>= -0.2'
                      },
                      {
                        from: 0,
                        to: 0.199999,
                        color: '#ffe37c',
                        name: '>= 0'
                      },
                      {
                        from: 0.2,
                        to: 0.399999,
                        color: '#d1f57e',
                        name: '>= 0.2'
                      },
                      {
                        from: 0.4,
                        to: 0.599999,
                        color: '#95e15b',
                        name: '>= 0.4'
                      },
                      {
                        from: 0.6,
                        to: 0.799999,
                        color: '#3dc55b',
                        name: '>= 0.6'
                      },
                      {
                        from: 0.8,
                        to: 0.999999,
                        color: '#009f4b',
                        name: '>= 0.8'
                      },
                      {
                        from: 1,
                        to: 1,
                        color: '#006d34',
                        name: '= 1'
                    }],
          data: [],
          series: [],
          correlation: [{ correlationCoefficient: null }],
          chartHasData: true,
          chartOptions: {
              chart: {
                height: 100,
                type: 'heatmap',
              },
              plotOptions: {
                heatmap: {
                  distributed: true,
                  reverseNegativeShade: true,
                  shadeIntensity: 0,
                  radius: 0,
                  useFillColorAsStroke: true,
                  colorScale: {
                    ranges: [
                      {
                        from: -1,
                        to: -0.800001,
                        color: '#000000',
                        name: '>= -1'
                      },
                      {
                        from: -0.8,
                        to: -0.600001,
                        color: '#ab003d',
                        name: '>= -0.8'
                      },
                      {
                        from: -0.6,
                        to: -0.400001,
                        color: '#e52143',
                        name: '>= -0.6'
                      },
                      {
                        from: -0.4,
                        to: -0.200001,
                        color: '#ff6223',
                        name: '>= -0.4'
                      },
                      {
                        from: -0.2,
                        to: -0.000001,
                        color: '#ffad4a',
                        name: '>= -0.2'
                      },
                      {
                        from: 0,
                        to: 0.199999,
                        color: '#ffe37c',
                        name: '>= 0'
                      },
                      {
                        from: 0.2,
                        to: 0.399999,
                        color: '#d1f57e',
                        name: '>= 0.2'
                      },
                      {
                        from: 0.4,
                        to: 0.599999,
                        color: '#95e15b',
                        name: '>= 0.4'
                      },
                      {
                        from: 0.6,
                        to: 0.799999,
                        color: '#3dc55b',
                        name: '>= 0.6'
                      },
                      {
                        from: 0.8,
                        to: 0.999999,
                        color: '#009f4b',
                        name: '>= 0.8'
                      },
                      {
                        from: 1,
                        to: 1,
                        color: '#006d34',
                        name: '= 1'
                    }]
                  }
                }
              },
              legend: {
                onItemHover: {
                  highlightDataSeries: true
                },
                position: 'bottom',
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
      },
      threshold: function(){
        this.getThreshold();
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
                y: this.correlation.correlationCoefficient
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
        },
        getThreshold: function(){
          if(this.appliedThreshold == true){
            var legendLength = this.chartOptions.plotOptions.heatmap.colorScale.ranges.length;
            for(let i = 0; i < legendLength; i++){
              this.chartOptions.plotOptions.heatmap.colorScale.ranges.pop();
            }
            console.log(this.chartOptions.plotOptions.heatmap.colorScale.ranges)
            this.chartOptions.plotOptions.heatmap.colorScale.ranges.push({ from: -1, to: this.threshold-0.000001, color: "#ffffff", name: ">= 1"})
            this.chartOptions.plotOptions.heatmap.colorScale.ranges.push({ from: this.threshold, to: 1, color: "#0000ff", name: ">= " + this.threshold})
          }
        }
    }
}
</script>