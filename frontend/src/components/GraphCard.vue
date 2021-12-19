<template>
    <div class="graphCard">
      <h1>{{graphName}}</h1>
        <apexcharts type="area" height="500" :options="chartOptions" :series="series"></apexcharts>
    </div>
</template>

<script>
import VueApexCharts from 'vue-apexcharts'

export default {
    name: 'GraphCard',
    components: {
        apexcharts: VueApexCharts,
    },
    props: {
      currencies: Array
    },
    watch: {
      currencies: function(){
        this.updateChart();
      }
    },
    data: function() {
      return {
        graphName: 'Graph',
        chartOptions: {
          xaxis: {
            type: "datetime"
          }
        },
        series: []
      }
    },
    methods: {
      updateChart() {
        let series = [];
        for (let key in this.currencies){
          let newData = [];
          let x = this.currencies[key].x;
          let y = this.currencies[key].y;
          for (let i in x){
            newData.push([parseInt(x[i]), parseInt(y[i])]);
          }
          series.push({
            name: this.currencies[key].name,
            data: newData
          });
        }
        this.series = series;
      }
    }
}
</script>