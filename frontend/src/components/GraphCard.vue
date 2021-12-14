<template>
    <div class="graphCard">
      <h1>{{graphName}}</h1>
        <apexcharts type="area" height="350" :options="chartOptions" :series="series"></apexcharts>
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
          chart: {
            id: 'basic-bar'
          },
          xaxis: {
            categories: []
          }
        },
        series: []
      }
    },
    methods: {
      updateChart() {
        console.log("updating..."); //DEBUG
        let first = true;
        let series = [];
        let categories = [];
        for (let key in this.currencies){
          if (first){
            categories = this.currencies[key].x;
            first = false;
          }
          series.push({
            name: this.currencies[key].name,
            data: this.currencies[key].y
          })
        }
        this.chartOptions.xaxis.categories = categories;
        this.series = series;
      }
    }
}
</script>