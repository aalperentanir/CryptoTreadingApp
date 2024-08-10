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
import { BookmarkFilledIcon } from "@radix-ui/react-icons";
import { Button } from "@/components/ui/button";
import { useDispatch, useSelector } from "react-redux";
import { addItemToWatchlist, getUserWatchlist } from "@/State/Watchllist/Action";

const Watchlist = () => {
  const { watchlist } = useSelector((store) => store);
  const dispatch = useDispatch();
  const handleRemoveToWatchlist = (value) => {
    dispatch(addItemToWatchlist({jwt:localStorage.getItem("jwt"), coinId:value}))
    console.log(value);
  };

  useEffect(() => {
    dispatch(getUserWatchlist(localStorage.getItem("jwt")));
  }, []);

  return (
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
            <TableHead>Price</TableHead>
            <TableHead className="text-right text-red-600">Remove</TableHead>
          </TableRow>
        </TableHeader>
        <TableBody>
          {watchlist.items.map((item, index) => (
            <TableRow key={index}>
              <TableCell className="font-medium flex items-center gap-2">
                <Avatar className="z-50">
                  <AvatarImage src={item.image}></AvatarImage>
                </Avatar>
                <span>{item.name}</span>
              </TableCell>
              <TableCell>{item.symbol.toUpperCase()}</TableCell>
              <TableCell>{item.total_volume}</TableCell>
              <TableCell>{item.market_cap}</TableCell>
              <TableCell>{item.market_cap_change_percentage_24h}%</TableCell>
              <TableCell>${item.current_price}</TableCell>
              <TableCell className="text-right">
                <Button
                  variant="ghost"
                  onClick={() => handleRemoveToWatchlist(item.id)}
                  size="icon"
                  className="h-10 w-10"
                >
                  <BookmarkFilledIcon className="w-6 h-6" />
                </Button>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </div>
  );
};

export default Watchlist;
