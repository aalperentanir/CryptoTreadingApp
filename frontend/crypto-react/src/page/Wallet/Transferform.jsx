import { Button } from "@/components/ui/button";
import { DialogClose } from "@/components/ui/dialog";
import { Input } from "@/components/ui/input";
import { transferMoney } from "@/State/Wallet/Action";

import React from "react";
import { useDispatch, useSelector } from "react-redux";
const Transferform = () => {
  const dispatch = useDispatch();
  const {wallet} = useSelector(store=>store);

  const [formData, setFormData] = React.useState({
    amount: "",
    comment: "",
    walletId: "",
  });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = () => {
    dispatch(transferMoney({
      jwt:localStorage.getItem("jwt"),
      walletId:formData.walletId,
      reqData:{
        amount:formData.amount,
        comment:formData.comment
      }
    }))
  };
  return (
    <div className="pt-10 space-y-5">
      <div>
        <h1 className="pb-1">Enter Amount</h1>
        <Input
          name="amount"
          onChange={handleChange}
          value={formData.amount}
          className="py-7"
          placeholder="Enter  amount"
        />
      </div>

      <div>
        <h1 className="pb-1">Wallet Id</h1>
        <Input
          name="walletId"
          onChange={handleChange}
          value={formData.walletId}
          className="py-7"
          placeholder="Enter  walletId"
        />
      </div>

      <div>
        <h1 className="pb-1">Comment</h1>
        <Input
          name="comment"
          onChange={handleChange}
          value={formData.comment}
          className="py-7"
          placeholder="Enter Comment"
        />
      </div>


      <DialogClose className="w-full">
        <Button onClick={handleSubmit} className="w-full py-7">
          Send
        </Button>
      </DialogClose>
    </div>
  );
};

export default Transferform;
