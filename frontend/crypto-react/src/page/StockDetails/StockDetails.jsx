import { Avatar, AvatarImage } from "@/components/ui/avatar";
import { Button } from "@/components/ui/button";
import {
  BookmarkFilledIcon,
  BookmarkIcon,
  DotIcon,
} from "@radix-ui/react-icons";
import React, { useEffect } from "react";

import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "@/components/ui/dialog";
import Treadingform from "./Treadingform";
import StockChart from "../Home/StockChart";
import { useDispatch, useSelector } from "react-redux";
import { useParams } from "react-router-dom";
import { fetchCoinDetails } from "@/State/Coin/Action";
import { addItemToWatchlist, getUserWatchlist } from "@/State/Watchllist/Action";
import { existInWatchlist } from "@/config/existInWatchlist";

const StockDetails = () => {

  const {coin, watchlist} =useSelector(store=>store)
  const dispatch = useDispatch();

  const {id} = useParams();

  console.log("coinId: ", id)

  useEffect(()=>{
    dispatch(fetchCoinDetails({coinId:id, jwt:localStorage.getItem("jwt")}))
    dispatch(getUserWatchlist(localStorage.getItem("jwt")));
  },[id])

  const handleAddCoinToWatchlist =() => {
    dispatch(addItemToWatchlist({jwt:localStorage.getItem("jwt"), coinId:coin.coinDetails?.id}))
  }
  return (
    <div className="p-5 mt-5">
      <div className="flex justify-between">
        <div className="flex gap-5 items-center">
          <div>
            <Avatar>
              <AvatarImage
                src={
                  coin.coinDetails?.image.large
                }
              />
            </Avatar>
          </div>
          <div>
            <div className="flex items-center gap-2">
              <p>{coin.coinDetails?.symbol.toUpperCase()}</p>
              <DotIcon className="text-gray-400" />
              <p className="text-gray-400">{coin.coinDetails?.name}</p>
            </div>
            <div className="flex items-end gap-2">
              <p className="tex-2xl font-bold">${coin.coinDetails?.market_data.current_price.usd}</p>
              <p className="text-red-600">
                <span>{coin.coinDetails?.market_data.market_cap_change_24h}</span>
                <span>({coin.coinDetails?.market_data.market_cap_change_percentage_24h}%)</span>
              </p>
            </div>
          </div>
        </div>

        <div className="flex items-center gap-4">
          <Button onClick={handleAddCoinToWatchlist}>
            {existInWatchlist(watchlist.items,coin.coinDetails) ? (
              <BookmarkFilledIcon className="h-6 w-6" />
            ) : (
              <BookmarkIcon className="h-6 w-6" />
            )}
          </Button>

          <Dialog>
            <DialogTrigger>
                <Button size="lg">Tread</Button>
            </DialogTrigger>
            <DialogContent>
              <DialogHeader>
                <DialogTitle>How much do you want to spend?</DialogTitle>
              </DialogHeader>
              <Treadingform/>
            </DialogContent>
          </Dialog>
        </div>
      </div>
      <div className="mt-14">
      <StockChart coinId={id}/>
      </div>
      
    </div>
  );
};

export default StockDetails;
