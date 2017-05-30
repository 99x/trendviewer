require('./index.html');
require('./style.css');

import Chart from 'chart.js';

var ctx = document.getElementById("myChart");

var myChart = new Chart(ctx, {
    type: 'line',
    data: {
        labels: ["27th", "28th", "29th", "30th", "01st", "2nd"],
        datasets: [{
            label: '# Mentions',
            data: [60, 100, 200, 150, 100, 0],
            borderWidth: 1
        }]
    },
    options: {
        responsive: false,
        animation: false,
        title: {
            display: true,
            text: '#Floodlk tweets'
        },
        scales: {
            xAxes: [{
                display: true,
                scaleLabel: {
                    display: true,
                    labelString: 'Days'
                }
            }],
            yAxes: [{
                display: true,
                scaleLabel: {
                    display: true,
                    labelString: 'Tweets'
                }
            }]
        }
    }
});