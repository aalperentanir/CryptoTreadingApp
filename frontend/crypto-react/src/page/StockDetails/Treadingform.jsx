import { Avatar, AvatarImage } from "@/components/ui/avatar"
import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { DotIcon } from "@radix-ui/react-icons"
import React, { useState } from "react"
const Treadingform = () => {

    const[orderType,setOrderType]=useState("BUY")

    const handleChange=()=>{

    }
    return(
        <div className="space-y-10 p-5">
            <div>
                <div className="flex gap-4 items-center justify-between">
                    <Input
                    className="py-7 focus:outline-none"
                    placeholder="Enter amount..."
                    onChange={handleChange}
                    type="number"
                    name="amount"
                    />

                    <div>
                        <p className="border tex-2xl flex justify-center items-center w-36 h-14 rounded-md">4567</p>
                    </div>
                </div>

                {false && <h1 className="text-red-500 text-center pt-4">Insufficent wallet balance to buy</h1>}
            </div>

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

        <div className="flex items-center justify-between">
            <p>Order type</p>
            <p>Market Order</p>
        </div>

        <div className="flex items-center justify-between">
            <p>{orderType=="BUY" ? "Avaliable Case":"Avaliable Quantity"}</p>
            <p>{orderType=="BUY" ? 12345:17.71}</p>
        </div>

        <div>
            <Button className={`w-full py-6 ${orderType=="SELL"?"bg-red-500":""}`}>
                {orderType}
            </Button>
            <Button variant="link" className="w-full mt-5 text-xl" onClick={()=>setOrderType(orderType=="BUY"?"SELL":"BUY")}>
                {orderType=="BUY"?"Or Sell":"Or Buy"}
            </Button>
        </div>

        </div>
    )
}

export default Treadingform