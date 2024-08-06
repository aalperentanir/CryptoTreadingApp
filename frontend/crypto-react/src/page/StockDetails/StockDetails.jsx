import { Avatar, AvatarImage } from "@/components/ui/avatar";
import { Button } from "@/components/ui/button";
import {
  BookmarkFilledIcon,
  BookmarkIcon,
  DotIcon,
} from "@radix-ui/react-icons";
import React from "react";

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

const StockDetails = () => {
  return (
    <div className="p-5 mt-5">
      <div className="flex justify-between">
        <div className="flex gap-5 items-center">
          <div>
            <Avatar>
              <AvatarImage
                src={
                  "https://cdn.pixabay.com/photo/2017/03/12/02/57/bitcoin-2136339_640.png"
                }
              />
            </Avatar>
          </div>
          <div>
            <div className="flex items-center gap-2">
              <p>BTC</p>
              <DotIcon className="text-gray-400" />
              <p className="text-gray-400">Bitcoin</p>
            </div>
            <div className="flex items-end gap-2">
              <p className="tex-2xl font-bold">$123</p>
              <p className="text-red-600">
                <span> -12345131321.123</span>
                <span>(-0.12345%)</span>
              </p>
            </div>
          </div>
        </div>

        <div className="flex items-center gap-4">
          <Button>
            {true ? (
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
      <StockChart/>
      </div>
      
    </div>
  );
};

export default StockDetails;
