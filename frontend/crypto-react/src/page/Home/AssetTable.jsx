import React from "react";
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
import { useNavigate } from "react-router-dom";
import { ScrollArea } from "@/components/ui/scroll-area";

const AssetTable = ({coin,category}) => {

  const navigate = useNavigate()
  return (
    <Table>
      <ScrollArea className={`${category == "all"?"h-[77.3vh]":"h-[82vh]"}`}>
              <TableHeader>
        <TableRow>
          <TableHead className="w-[100px]">Coin</TableHead>
          <TableHead>Symbol</TableHead>
          <TableHead>Volume</TableHead>
          <TableHead>Market Cap</TableHead>
          <TableHead>24h</TableHead>
          <TableHead className="text-right">Price</TableHead>
        </TableRow>
      </TableHeader>
      <TableBody>
        {coin.map((item, index) => (
          <TableRow key={item.id}>
            <TableCell onClick={()=> navigate(`/market/${item.id}`)} className="font-medium flex items-center gap-2">
              <Avatar className="z-50">
                <AvatarImage src={item.image}/>
              </Avatar>
              <span>{item.name}</span>
            </TableCell>
            <TableCell>{item.symbol.toUpperCase()}</TableCell>
            <TableCell>{item.total_volume}</TableCell>
            <TableCell>{item.market_cap}</TableCell>
            <TableCell>{item.price_change_percentage_24h}</TableCell>
            <TableCell className="text-right">${item.current_price}</TableCell>
          </TableRow>
        ))}
      </TableBody>
      </ScrollArea>

    </Table>
  );
};

export default AssetTable;