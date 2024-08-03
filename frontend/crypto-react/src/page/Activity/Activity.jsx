import React from "react"
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

const Activity = () => {
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
          {[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1].map((item, index) => (
            <TableRow key={index}>
              <TableCell>
                <p>2024/08/02</p>
                <p className="text-gray-400">11:54:01</p>
              </TableCell>
              <TableCell className="font-medium flex items-center gap-2">
                <Avatar className="z-50">
                  <AvatarImage src="https://cdn.pixabay.com/photo/2017/03/12/02/57/bitcoin-2136339_640.png"></AvatarImage>
                </Avatar>
                <span>Bitcoin</span>
              </TableCell>
              <TableCell>$6441</TableCell>
              <TableCell>$6331</TableCell>
              <TableCell>BUY</TableCell>
              <TableCell>-</TableCell>
              <TableCell className="text-right">$177</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </div>
    )
}

export default Activity