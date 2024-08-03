import { Button } from "@/components/ui/button";
import React, { useState } from "react";
import ReactApexChart from "react-apexcharts";

const timeSeries = [
  {
    keyword: "DIGITAL_CURRENCY_DAILY",
    key: "Time Series(Daily)",
    lable: "1 Day",
    value: 1,
  },
  {
    keyword: "DIGITAL_CURRENCY_WEEKLY",
    key: "Weekly Time Series",
    lable: "1 Week",
    value: 7,
  },
  {
    keyword: "DIGITAL_CURRENCY_MONTLY",
    key: "Monthly Time Series",
    lable: "1 Month",
    value: 30,
  },
];
const StockChart = () => {
  const [activeLable, setActiveLable] = useState("1 Day");
  const series = [
    {
      data: [
        [1720012227767, 59806.87425955481],
        [1720015357903, 60506.81684105061],
        [1720019116910, 60217.68768076043],
        [1720022579727, 60119.6569758108],
        [1720026687941, 60377.64969349297],
        [1720029981455, 60324.50778147703],
        [1720033568389, 60095.36814276449],
        [1720037437963, 59745.8278835818],
        [1720040490281, 59563.92273028924],
        [1720044623374, 60147.75475815409],
        [1720048081617, 60242.774086307996],
        [1720051731868, 60182.99341375096],
        [1720054958125, 60398.52778331812],
        [1720059091716, 58128.71854045257],
      ],
    },
  ];

  const options = {
    chart: {
      id: "area-datetime",
      type: "area",
      height: 350,
      zoom: {
        autoScaleYaxis: true,
      },
    },
    dataLabels: {
      enabled: false,
    },
    xaxis: {
      type: "datetime",
      tickAmount: 6,
    },
    colors: ["#3399ff"],

    markers: {
      colors: ["#fff"],
      strokeColor: "#fff",
      size: 0,
      strokeWidth: 1,
      style: "hollow",
    },
    tooltip: {
      theme: "dark",
    },
    fill: {
      type: "gradient",
      gradient: {
        shadeIntensity: 1,
        opacityFrom: 0.7,
        opacityTo: 0.9,
        stops: [0, 100],
      },
    },
    grid: {
      borderColor: "#47535E",
      strokeDashArray: 4,
      show: true,
    },
  };

  const handleActiveLable = (value) => {
    setActiveLable(value);
  };

  return (
    <div>
      <div className="space-x-3">
        {timeSeries.map((item) => (
          <Button
            onClick={() => handleActiveLable(item.lable)}
            variant={activeLable == item.lable ? "" : "outline"}
            key={item.lable}
          >
            {item.lable}
          </Button>
        ))}
      </div>
      <div id="chart-timelines">
        <ReactApexChart
          options={options}
          series={series}
          height={450}
          type="area"
        />
      </div>
    </div>
  );
};

export default StockChart;
