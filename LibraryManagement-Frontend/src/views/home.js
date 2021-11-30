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
  mounted: function loadLibrarySchedule(){
        const url = "/libraryTimeslots/get"
        
        // create array of the next 7 days
        var today = new Date()
        today.setDate(today.getDate()+1)
        var thisWeek = [
        
        for (let i = 0 ; i < 7 ; i++){
            var nextDay = new Date()
            nextDay.setDate(today.getDate()+i)
            const day = (nextDay.getDate() < 10) ? "0" + nextDay.getDate() : nextDay.getDate()
            thisWeek.push(nextDay.getFullYear()+'-'+(nextDay.getMonth()+2)+'-'+day)
        }       
        AXIOS.get(url)
        .then(response => {
            // JSON responses are automatically parsed.
            let r = response.data
            (r).forEach(timeslot => {

                // parse date
                const date = timeslot.date
                const year = "20" + date.split("-")[0].substring(2,4)
                const month = date.split("-")[1]
                const day = date.split("-")[2]
                const timeslotDate = year+'-'+month+'-'+day

                // parse times
                const startTime = timeslot.startTime
                const endTime = timeslot.endTime

                console.log(startTime)
                console.log(endTime)



            });
        })
        .catch(e => {
            this.errorTitle = e
        });

        // display the timeslots
        // get the element that will display
        const content = document.getElementById("library-schedule")
    }
}