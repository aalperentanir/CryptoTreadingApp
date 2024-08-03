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
import { BookmarkFilledIcon } from "@radix-ui/react-icons";
import { Button } from "@/components/ui/button";

const Watchlist = () => {
    const handleRemoveToWatchlist=(value)=>{
        console.log(value)
    }
    return(
        <div className="p-5 lg:px-20">
        <h1 className="font-bold text-3xl pb-5">Watchlist</h1>
      <Table className="border">
        <TableHeader>
          <TableRow>
            <TableHead className="py-5">Coin</TableHead>
            <TableHead>Symbol</TableHead>
            <TableHead>Volume</TableHead>
            <TableHead>Market Cap</TableHead>
            <TableHead>24h</TableHead>
            <TableHead >Price</TableHead>
            <TableHead className="text-right text-red-600">Remove</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          {[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1].map((item, index) => (
            <TableRow key={index}>
              <TableCell className="font-medium flex items-center gap-2">
                <Avatar className="z-50">
                  <AvatarImage src="https://cdn.pixabay.com/photo/2017/03/12/02/57/bitcoin-2136339_640.png"></AvatarImage>
                </Avatar>
                <span>Bitcoin</span>
              </TableCell>
              <TableCell>BTC</TableCell>
              <TableCell>39636982160</TableCell>
              <TableCell>1274010947813</TableCell>
              <TableCell>0.10745</TableCell>
              <TableCell >$64536</TableCell>
              <TableCell className="text-right">
                <Button variant="ghost" onClick={()=>handleRemoveToWatchlist(item.id)} size="icon" className="h-10 w-10">
                    <BookmarkFilledIcon className="w-6 h-6"/>
                </Button>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </div>
    )
}

export default Watchlist