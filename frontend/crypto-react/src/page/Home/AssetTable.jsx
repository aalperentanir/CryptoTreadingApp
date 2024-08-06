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

import { AvatarIcon } from "@radix-ui/react-icons";
import { Avatar, AvatarImage } from "@/components/ui/avatar";
import { useNavigate } from "react-router-dom";

const AssetTable = () => {

  const navigate = useNavigate()
  return (
    <Table>
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
        {[1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1].map((item, index) => (
          <TableRow key={index}>
            <TableCell onClick={()=> navigate(`/market/bitcoin`)} className="font-medium flex items-center gap-2">
              <Avatar className="z-50">
                <AvatarImage src="https://cdn.pixabay.com/photo/2017/03/12/02/57/bitcoin-2136339_640.png"></AvatarImage>
              </Avatar>
              <span>Bitcoin</span>
            </TableCell>
            <TableCell>BTC</TableCell>
            <TableCell>39636982160</TableCell>
            <TableCell>1274010947813</TableCell>
            <TableCell>0.10745</TableCell>
            <TableCell className="text-right">$64536</TableCell>
          </TableRow>
        ))}
      </TableBody>
    </Table>
  );
};

export default AssetTable;
