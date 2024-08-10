import React, { useEffect } from "react";
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
import { getUserAsset } from "@/State/Asset/Action";

const Portfolio = () => {
  const dispatch = useDispatch();
  const { asset } = useSelector((store) => store);

  useEffect(() => {
    dispatch(getUserAsset(localStorage.getItem("jwt")));
  }, []);
  return (
    <div className="p-5 lg:px-20">
      <h1 className="font-bold text-3xl pb-5">Portfolio</h1>
      <Table>
        <TableHeader>
          <TableRow>
            <TableHead className="">Assets</TableHead>
            <TableHead>Price</TableHead>
            <TableHead>Current Price</TableHead>
            <TableHead>Unit</TableHead>
            <TableHead>Change</TableHead>
            <TableHead>Change%</TableHead>
            <TableHead className="text-right">Volume</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          {asset.userAssets.map((item, index) => (
            <TableRow key={index}>
              <TableCell className="font-medium flex items-center gap-2">
                <Avatar className="z-50">
                  <AvatarImage src={item.coin.image}></AvatarImage>
                </Avatar>
                <span>{item.coin.name}</span>
              </TableCell>
              <TableCell>${item.buyPrice}</TableCell>
              <TableCell
                style={{
                  color:
                    item.coin.current_price < item.buyPrice ? "red" : "green",
                }}
              >
                ${item.coin.current_price}
              </TableCell>

              <TableCell>{item.quantity}</TableCell>
              <TableCell
                style={{
                  color: item.coin.price_change_24h < 0 ? "red" : "green",
                }}
              >
                {item.coin.price_change_24h}
              </TableCell>
              <TableCell
                style={{
                  color:
                    item.coin.price_change_percentage_24h < 0 ? "red" : "green",
                }}
              >
                {item.coin.price_change_percentage_24h}
              </TableCell>
              <TableCell className="text-right">
                {item.coin.total_volume}
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </div>
  );
};

export default Portfolio;
