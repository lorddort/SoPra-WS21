<template>
    <div id="chart">
        <li>Under</li>
        <li>{{taggedValue}}</li>
        <li>{{series}}</li>
        <li>Above</li>
        <apexcharts type="heatmap" height="350" :options="chartOptions" :series="series"></apexcharts>
    </div>
    
</template>

<script>
import VueApexCharts from 'vue-apexcharts'

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
                        from: -30,
                        to: 5,
                        name: 'low',
                        color: '#00A100'
                        },
                        {
                        from: 6,
                        to: 20,
                        name: 'medium',
                        color: '#128FD9'
                        },
                        {
                        from: 21,
                        to: 45,
                        name: 'high',
                        color: '#FFB200'
                        },
                        {
                        from: 46,
                        to: 55,
                        name: 'extreme',
                        color: '#FF0000'
                        }]
                    }
                  }
                },
              dataLabels: {
                enabled: false
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
        updateMap() {
          var series = [];
          for(var i = 0; i < this.taggedValue.length; i++){
            var dataArray = [];
            for(let j = 0; j<this.taggedValue.length; j++){
              dataArray.push({
                x: this.taggedValue[j].name,
                y: Math.floor(Math.random()*30)             //TODO get Korrelation from backend
              })
            }
            series.push({
              name: this.taggedValue[i].name,
              data: dataArray
            });
          }
          this.series = series;
        }
    },
    created: function(){
      this.updateMap();
    }
}
</script>