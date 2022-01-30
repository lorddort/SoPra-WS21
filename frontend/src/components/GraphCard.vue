<template>
    <div class="graphCard">
        <apexcharts type="area" :options="chartOptions" :series="series"></apexcharts>
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
      rawData: [],
      timeFrame: {}
    },
    watch: {
      rawData: function(){
        this.loadData();
      },
      timeFrame: function(){
        this.chartOptions.xaxis = {
          min: this.timeFrame.from,
          max: this.timeFrame.to
        }
        this.updateTimeFrameChartType();
        this.updateSeriesData();
      }
    },
    data: function() {
      return {
        //apex charts config =========
        graphName: 'Graph',
        chartOptions: {
          xaxis: {
            type: "datetime"
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
        //end of apex charts config==========
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
      // load data from rawData into graph dependend on selected time frame
      // used for currencies that arent yet shown in the graph
      loadData() {
        this.series = [];
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
      // update series data depending on selected time frame
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
      // returns specified price chart from rawData object depending on selection
      getPriceChartFromRawDataObj(rawDataObj){
        switch (this.loadedChartType){
          case this.charts.minutelyChart:
            return this.copyDateTable(rawDataObj.minutelyChart.prices);
          case this.charts.hourlyChart:
            return this.copyDateTable(rawDataObj.hourlyChart.prices);
          case this.charts.dailyChart:
            return this.copyDateTable(rawDataObj.dailyChart.prices);
          default:
            console.log("No valid time frame set!");
            break;
        }
      },
      // set new time frame
      updateTimeFrameChartType(){
        switch (this.timeFrame.frameType){
          case this.frames.day:
            this.loadedChartType = this.charts.minutelyChart;
            break;
          case this.frames.week:
            this.loadedChartType = this.charts.hourlyChart;
            break;
          case this.frames.month:
            this.loadedChartType = this.charts.hourlyChart;
            break;
          case this.frames.year:
            this.loadedChartType = this.charts.dailyChart;
            break;
          default:
            console.log("No valid data time chart available!");
            break;
        }
      },
      // helper function to create a new array from a given one as it is needed to trigger 
      // apexcharts update function
      copyDateTable(copyFrom){
        let copyTo = [];
        for (let i in copyFrom){
          let tuple = copyFrom[i];
          if (tuple[0] <= this.timeFrame.from){
            continue;
          }
          copyTo.push([parseFloat(tuple[0]), parseFloat(tuple[1])]);
        }
        return copyTo;
      },
      //converts human readable time to unixTime
        toUnixTime: function(days, hours, minutes, seconds){
            return  days * 24 * 60 * 60 * 1000 +
                hours * 60 * 60 * 1000+
                minutes * 60 * 1000+
                seconds;
        }
    },
    mounted(){
      this.loadedChartType = this.charts.minutelyChart;
      this.chartOptions.min = this.timeFrame.from;
      this.chartOptions.max = this.timeFrame.to;
    }
}
</script>