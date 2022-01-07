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
        this.chartOptions.xaxis.max = timeFrame.to; 
        this.updateTimeFrameChartType();
        this.updateSeriesData();
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
        },
        frames: {
            day: 0,
            week: 1,
            month: 2,
            year: 3,
            custom: 4
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
        let newSeries = []
        for (let i in this.series){
          let seriesObj = this.series[i];
          for (let j in this.rawData){
            let dataObj = this.rawData[j];
            if (dataObj.name == seriesObj.name){
              seriesObj.data = this.getPriceChartFromRawDataObj(dataObj);
              newSeries.push(seriesObj);
            }
          }
        }
        this.series = newSeries;
      },
      getPriceChartFromRawDataObj(rawDataObj){
        switch (this.loadedChartType){
          case this.charts.minutelyChart:
            return this.copyDateTable(rawDataObj.minutelyChart.prices);
          case this.charts.hourlyChart:
            return this.copyDateTable(rawDataObj.hourlyChart.prices);
          case this.charts.dailyChart:
            return this.copyDateTable(rawDataObj.dailyChart.prices);
          default:
            console.log("Surprise, code failed");
            break;
            //TODO doesn't go into right case
        }
      },
      updateTimeFrameChartType(){
        switch (this.timeFrame.frameType){
          case this.frames.day:
            this.loadedChartType = this.charts.minutelyChart;
            break;
          case this.frames.week:
            this.loadedChartType = this.charts.hourlyChart;
            break;
          case this.frames.month:
            this.loadedChartType = this.charts.dailyChart;
            break;
          case this.frames.year:
            this.loadedChartType = this.charts.dailyChart;
            break;
          default:
            console.log("Code broke somehow");
            break;
        }
        console.log("TimeFrame set to: " + this.timeFrame.frameType);
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