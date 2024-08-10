import React, { useEffect } from "react"
import {
    Table,
    TableBody,
    TableCaption,
    TableCell,
    TableHead,
    TableHeader,
    TableRow,
  } from "@/components/ui/table";
  import { Avatar, AvatarImage } from "@/components/ui/avatar";
import { useDispatch, useSelector } from "react-redux";
import { getAllOrdersForUser } from "@/State/Order/Action";
import { calculateEarn } from "@/config/calculateEarnValue";

const Activity = () => {
  const dispatch = useDispatch();
  const {order} = useSelector(store=> store);
//  console.log("Tarih:", order..toISOString().split('T')[0]); // Tarih kısmı
//console.log("Saat:", date.toISOString().split('T')[1]);

  useEffect(()=>{
    dispatch(getAllOrdersForUser({jwt:localStorage.getItem("jwt")}))
  },[])
    return(
        <div className="p-5 lg:px-20">
        <h1 className="font-bold text-3xl pb-5">Activity</h1>
      <Table className="border">
        <TableHeader>
          <TableRow>
            <TableHead className="py-5">Date & Time</TableHead>
            <TableHead>Coin</TableHead>
            <TableHead>Buy Price</TableHead>
            <TableHead>Selling Price</TableHead>
            <TableHead>Order Type</TableHead>
            <TableHead >Earn/Loss</TableHead>
            <TableHead className="text-right">Value</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          {order.orders.map((item, index) => (
            <TableRow key={index}>
              <TableCell>
                <p>{item.timeStamp.split('T')[0]}</p>
                <p className="text-gray-400">{item.timeStamp.split('T')[1]}</p>
              </TableCell>
              <TableCell className="font-medium flex items-center gap-2">
                <Avatar className="z-50">
                  <AvatarImage src={item.orderItem.coin.image}></AvatarImage>
                </Avatar>
                <span>{item.orderItem.coin.name}</span>
              </TableCell>
              <TableCell>${item.orderItem.buyPrice}</TableCell>
              <TableCell>${item.orderItem.sellPrice}</TableCell>
              <TableCell>{item.orderType}</TableCell>
              <TableCell>{calculateEarn(item)}</TableCell>
              <TableCell className="text-right">{item.price}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </div>
    )
}

export default Activity