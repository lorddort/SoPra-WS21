<template>
    <div class="graphCard">
        <h1>Graph Card</h1>
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
    data: function() {
        return {
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
          }]
        }
    }
}
</script>