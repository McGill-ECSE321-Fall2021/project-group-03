import MenuBar from "../components/MenuBar.vue";
import axios from 'axios'

var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})

export default {
  name: "Home",
  components: {
    MenuBar,
  },

  data(){
      return {
          librarySchedule: [],
          response: []
      }
  },
  mounted: function loadLibrarySchedule(){
        const url = "/libraryTimeslots/get"
        
        // create array of the next 7 days
        var today = new Date()
        today.setDate(today.getDate()+1)
        var thisWeek = []
        
        for (let i = 0 ; i < 7 ; i++){
            var nextDay = new Date()
            nextDay.setDate(today.getDate()+i)
            const day = (nextDay.getDate() < 10) ? "0" + nextDay.getDate() : nextDay.getDate()
            thisWeek.push(nextDay.getFullYear()+'-'+(nextDay.getMonth()+2)+'-'+day)
        }

        AXIOS.get(url).then(response => {
            // JSON responses are automatically parsed.
            this.librarySchedule = response.data
        })
        .catch(e => {
            this.errorTitle = e
        });

        // display the timeslots
        // get the element that will display
        const content = document.getElementById("library-schedule")
    }
}