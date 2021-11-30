import axios from 'axios'
import MenuBar from '../components/MenuBar.vue'

var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})


    export default{
        data(){ 
            return { 
                displayRooms: [],
                allRooms: [],
                eventRooms : [], 
                partyRooms : [], 
                newRoom: '', 
                errorRoom: '', 
                response: [] 
            }
        },
        created: function(){
            AXIOS.get('/rooms/get').then(response => {
                this.allRooms = response.data
                this.displayRooms = this.allRooms
            }).catch(e => {
                this.errorTitle = e
            })

            for (let i = 0; i < this.allRooms.length; i++) {
                if (this.allRooms[i].roomType == "Event") {
                    this.eventRooms.push(this.allRooms[i]);
                } else {
                    this.partyRooms.push(this.allRooms[i]);
                }
            }

            console.log(this.eventRooms)
            console.log(this.partyRooms)
        },
        
        methods:  {
            clickRow(roomId) {
                console.log(roomId)
                let goodUrl = "/#/browse/room/" + roomId;
                localStorage.setItem("roomId", roomId);
                window.location.href = goodUrl;
            },

            // selectFilter() {
            //     let filterValue = document.getElementById("type-selector").value;

            //     if (filterValue == "Event") {
            //         this.displayRooms = this.eventRooms
            //         console.log(this.displayRooms)
            //     } else if (filterValue == "Study") {
            //         this.displayRooms = this.partyRooms
            //         console.log(this.displayRooms)
            //     }
            // }
        
        },
        components: {
            MenuBar,
        }
    }