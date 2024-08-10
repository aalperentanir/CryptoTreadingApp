import { Avatar, AvatarImage } from "@/components/ui/avatar"
import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { getAssetDetails } from "@/State/Asset/Action"
import { payOrder } from "@/State/Order/Action"
import { getUserWallet } from "@/State/Wallet/Action"
import { DotIcon } from "@radix-ui/react-icons"
import React, { useEffect, useState } from "react"
import { useDispatch, useSelector } from "react-redux"

const Treadingform = () => {
  const dispatch = useDispatch();
    const[orderType,setOrderType]=useState("BUY")
    const [amount,setAmount] = useState(0);
    const [quantity, setQuantity] = useState(0)
    const {coin, wallet, asset} = useSelector(store => store);

    //console.log("tradingFormCoin",coin)
    //console.log("treadingFormWallet",wallet.userWallet)
    const handleChange=(e)=>{
      const amount = e.target.value;
      setAmount(amount)
      
      const volume = calculateCost(amount, coin.coinDetails.market_data.current_price.usd);

      console.log(volume)
      setQuantity(volume)
    }

    const calculateCost = (amount, price) => {
      let volume = amount / price
      let decimalPlaces = Math.max(2,price.toString().split(".")[0].length)

      return volume.toFixed(decimalPlaces)
    }

    useEffect(()=> {
      dispatch(getUserWallet(localStorage.getItem("jwt")))
      dispatch(getAssetDetails({coinId:coin.coinDetails.id, jwt:localStorage.getItem("jwt")}))
    },[])

    const handleBuyCrypto =()=> {
      dispatch(payOrder({
        jwt:localStorage.getItem("jwt"),
        amount,
        orderData:{
          coinId:coin.coinDetails?.id,
          quantity,
          orderType
        }
      }))
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
                        <p className="border tex-2xl flex justify-center items-center w-36 h-14 rounded-md">{quantity}</p>
                    </div>
                </div>

                {false && <h1 className="text-red-500 text-center pt-4">Insufficent wallet balance to buy</h1>}
            </div>

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
              <p>{coin.coinDetails.symbol.toUpperCase()}</p>
              <DotIcon className="text-gray-400" />
              <p className="text-gray-400">{coin.coinDetails?.name}</p>
            </div>
            <div className="flex items-end gap-2">
              <p className="tex-2xl font-bold">${coin.coinDetails?.market_data.current_price.usd}</p>
              <p className="text-red-600">
                <span> {coin.coinDetails?.market_data.market_cap_change_24h}</span>
                <span>({coin.coinDetails?.market_data.market_cap_change_percentage_24h}%)</span>
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
            <p>{orderType=="BUY" ? "$"+wallet.userWallet?.balance : (asset.assetDetails?.quantity || 0 )}</p>
        </div>

        <div>
            <Button onClick= {handleBuyCrypto} className={`w-full py-6 ${orderType=="SELL"?"bg-red-500":""}`}>
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