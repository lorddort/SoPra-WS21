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
      rawData: Array,
      timeFrame: {}
    },
    watch: {
      rawData: function(){
        this.loadData();
      },
      timeFrame: function(){
        this.chartOptions.xaxis.min = timeFrame.from;
        this.chartOptions.xaxix.max = timeFrame.to; 
        updateSeriesData();
      }
    },
    data: function() {
      return {
        graphName: 'Graph',
        chartOptions: {
          xaxis: {
            type: "datetime",
            min: undefined,
            max: undefined
          },
          yaxis: {
            labels: {
              formatter: function (value){
                return value.toFixed(2);
              }
            }
          },
          dataLabels: {
            enabled: false,
            formatter: function(value){
              if (value > 0.00){
                return value.toFixed(2);
              } else {
                return value.toFixed(4);
              }
            }
          }
        },
        series: [],
        loadedChartType: undefined,
        charts: {
          minutelyChart: 0,
          hourlyChart: 1,
          dailyChart: 2
        }
      }
    },
    methods: {
      loadData() {
        for (let i in this.rawData){
          if (!this.series.some(obj => obj.name == this.rawData[i].name)){
            let dataObj = {
              name: this.rawData[i].name,
              data: []
            }
            switch (this.loadedChartType){
              case this.charts.minutelyChart:
                dataObj.data = this.copyDateTable(this.rawData[i].minutelyChart.prices);
                break;
              case this.charts.hourlyChart:
                dataObj.data = this.copyDateTable(this.rawData[i].hourlyChart.prices);
                break;
              case this.charts.dailyChart:
                dataObj.data = this.copyDateTable(this.rawData[i].dailyChart.prices);
                break;
              default:
                console.log("Surprise, code failed");
                break;
            }
            this.series.push(dataObj);
          }
        }
      },
      updateSeriesData() {
        //update on time frame change
      },
      copyDateTable(copyFrom){
        let copyTo = [];
        for (let i in copyFrom){
          let tuple = copyFrom[i];
          copyTo.push([parseFloat(tuple[0]), parseFloat(tuple[1])]);
        }
        return copyTo;
      }
    },
    mounted(){
      this.loadedChartType = this.charts.minutelyChart;
      this.chartOptions.min = this.timeFrame.from;
      this.chartOptions.max = this.timeFrame.to;
    }
}
</script>