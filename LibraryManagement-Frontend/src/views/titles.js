import axios from 'axios'
import joinArrays from 'webpack-merge/lib/join-arrays'
import MenuBar from '../components/MenuBar.vue'

var config = require('../../config')

var frontendUrl = 'http://' + config.dev.host + ':' + config.dev.port
var backendUrl = 'http://' + config.dev.backendHost + ':' + config.dev.backendPort

var AXIOS = axios.create({
  baseURL: backendUrl,
  headers: { 'Access-Control-Allow-Origin': frontendUrl }
})
    export default{

        name: "Browse",

        components: {
            MenuBar
        },

        data(){
            return {
                titles: [],
                newTitle: '',
                errorTitle: '',
                response: [],
            }
        },
        created: function(){

            // Initializing persons from backend
            AXIOS.get('/titles/get')
            .then(response => {
            // JSON responses are automatically parsed.
            this.titles = response.data
            // console.log(this.titles)
            })
            .catch(e => {
            this.errorTitle = e
            })

        },
        
        methods:  {
            reveal(){
                const filterBtn = document.querySelector(".more-filters")

                if (filterBtn.hidden){
                    filterBtn.hidden = false
                }
                else{
                    filterBtn.hidden = true
                }
            },

            filterSearch(){
                // get filter values
                const type = document.getElementById("type").value
                const genre = document.getElementById("genre").value

                const availability = document.getElementById("availability").checked

                console.log(availability)

                const name = document.getElementById("title-name").value

                
                const tableData = document.getElementsByClassName("table-data")
                // reset
                Array.from(tableData).forEach(entry => {
                    entry.style.display = ''
                });
                
                // do not need to filter by type if == all
                if (type != "all"){
                    Array.from(tableData).forEach(entry => {
                        // check the content of title-type <td>
                        Array.from(entry.children).forEach(column => {
                            if(column.className == "title-type"){
                                if (column.innerHTML != type){
                                    entry.style.display = 'none'
                                }
                            }
                        });
                    });
                }

                // do not need to filter by genre if == all
                if (genre != "all") {
                    Array.from(tableData).forEach(entry => {
                        // check the content of title-type <td>
                        Array.from(entry.children).forEach(column => {
                            if(column.className == "title-genre"){
                                if (column.innerHTML != genre){
                                    entry.style.display = 'none'
                                }
                            }
                        });
                    });
                }

                if(name != ""){
                    Array.from(tableData).forEach(entry => {
                        // check the content of title-type <td>
                        Array.from(entry.children).forEach(column => {
                            if(column.className == "title-name"){
                                if (column.innerHTML != name){
                                    entry.style.display = 'none'
                                }
                            }
                        });
                    });
                }

                if (availability) {
                    Array.from(tableData).forEach(entry => {
                        // check the content of title-type <td>
                        Array.from(entry.children).forEach(column => {
                            if(column.className == "title-genre"){
                                if (column.innerHTML != genre){
                                    entry.style.display = 'none'
                                }
                            }
                        });
                    });
                }


            },

            displayAvailability: function(isAvailable) {
                if (isAvailable) {
                    return "✓"
                } else {
                    return "✗"
                }
            },

            routerlink: function (newUrl) {
                let url = "browse/title/";
                url += newUrl;
                return url
            },

            clickRow(titleName) {
                console.log(titleName)
                let goodUrl = "/#/browse/title/" + titleName;
                window.location.href = goodUrl;
            },
        }
    }