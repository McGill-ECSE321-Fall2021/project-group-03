import axios from 'axios'
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
            // createTitle: function(name, description, genre, isAvailable, titleType){
                
                
            //     AXIOS.post('/titles/create/'.concat(name).concat("?description=").concat(description).concat("&genre=").concat(genre).concat("&isAvailable=").concat(isAvailable).concat("&titleType=").concat(titleType), {}, {}).then(response => {
            //     var t = new TitleDto(name, description, genre, isAvailable, titleType)
            //     this.titles.push(t)
            //     this.newTitle = ''
            //     })
            //     .catch(e => {
            //         var errorMsg = e.response.data.message
            //         console.log(errorMsg)
            //         this.errorTitle = errorMsg
            //       })
            // },

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
                console.log(goodUrl)
                window.location.href = goodUrl;
            },
        }
    }