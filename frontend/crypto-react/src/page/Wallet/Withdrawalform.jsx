import { Button } from "@/components/ui/button";
import { DialogClose } from "@/components/ui/dialog";
import { Input } from "@/components/ui/input";
import { withdrawalRequest } from "@/State/Withdrawal/Action";
import React from "react";
import { useDispatch, useSelector } from "react-redux";

const Witddrawalform = () => {
  const [amount, setAmount] = React.useState("");
  const dispatch = useDispatch();
  const {wallet,withdrawal} = useSelector(store=>store);
  
  const handleChange = (e) => {
    setAmount(e.target.value);
  };

  const handleSubmit = ()=>{
    dispatch(withdrawalRequest({amount,jwt:localStorage.getItem("jwt")}))
    console.log(amount)
  }
  return (
    <div className="pt-10 space-y-5">
      <div className="flex justify-between items-center rounded-md bg-slate-300 tex-xl font-bold px-5 py-4">
        <p>Available balance</p>
        <p>${wallet.userWallet?.balance}</p>
      </div>
      <div className="flex flex-col items-center">
        <h1>Enter Withdrawal amount</h1>
        <div className="flex items-center justify-center">
          <Input
            onChange={handleChange}
            value={amount}
            className="withdrawalInput py-7 border-none outline-none focus:outline-none px-0 text-2xl text-center"
            placeholder="Enter a amount"
            type="number"
          />
        </div>
      </div>
      <div>
        <p className="pb-2">Transfer to</p>
        <div className="flex items-center gap-5 border px-5 py-2 rounded-md">
            <img className="h-8 w-8" src="https://cdn.pixabay.com/photo/2015/10/14/18/43/bank-988164_640.png" alt="" />
            <div>
                <p className="text-xl font-bold">{withdrawal.paymentDetails?.bankName ? `${withdrawal.paymentDetails.bankName}` : "X Bank"}</p>
                <p className="text-xs">{withdrawal.paymentDetails?.accountNumber}</p>
            </div>
        </div>
      </div>
      <DialogClose className="w-full">
      <Button onClick={handleSubmit} className="w-full py-7 text-xl">
        Withdrawal
      </Button>
      </DialogClose>

    </div>
  );
};

export default Witddrawalform;
