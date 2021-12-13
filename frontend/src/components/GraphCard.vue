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
      currencies: []
    },
    data: function() {
      return {
        graphName: 'Graph',
        chartOptions: {
          chart: {
            id: 'basic-bar'
          },
          xaxis: {
            categories: [1991, 1992, 1993, 1994, 1995, 1996, 1997, 1998]
          }
        },
        series: [{
          name: 'series-1',
          data: [30, 40, 45, 50, 49, 60, 70, 91]
        },
        { name: 's2',
          data: [60, 60, 56, 50, 40, 20, 25, 30]}]
      }
    },
    created: function(){
      
    }
}
</script>