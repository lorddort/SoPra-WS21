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
      currencies: function(val, oldVal){
        this.currencies = val;
        let first = true;
        let update = {
          xaxis: {
            categories: []
          },
          series : [{
            name: String,
            data: Array
          }]
        }
        for (let key in this.currencies){
          if (first){
            update.xaxis.categories = this.currencies[key].x;
            first = false;
          }
          update.series.push({
            name: this.currencies[key].name,
            data: this.currencies[key].y
          })
        }
        this.apexcharts.updateOptions(update);
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
    }
}
</script>