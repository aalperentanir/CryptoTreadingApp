import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { RadioGroup, RadioGroupItem } from "@/components/ui/radio-group";
import { paymentHandler } from "@/State/Wallet/Action";
import { DotFilledIcon } from "@radix-ui/react-icons";
import React from "react";
import { useDispatch } from "react-redux";

const Topupform = () => {
  const [amount, setAmount] = React.useState("");
  const [paymentMethod, setPaymentMethod] = React.useState("STRIPE");

  const dispatch = useDispatch();

  const handlePaymentMethodChange = (value) => {
    setPaymentMethod(value);
  };

  const handleChange = (e) => {
    setAmount(e.target.value);
  };

  const handleSubmit = () => {
    console.log(amount, paymentMethod);
    dispatch(
      paymentHandler({
        jwt: localStorage.getItem("jwt"),
        paymentMethod,
        amount,
      })
    );
  };

  return (
    <div className="pt-10 space-y-5">
      <div>
        <h1 className="pb-1">Enter Amount</h1>
        <Input
          onChange={handleChange}
          value={amount}
          className="py-7 text-lg"
          placeholder="$12345"
        />
      </div>

      <div>
        <h1 className="pb-1">Select payment method</h1>
        <RadioGroup
          onValueChange={handlePaymentMethodChange}
          className="flex"
          defaultValue="STRIPE"
        >
          <div className="flex items-center space-x-2 border p-3 px-5 rounded-md w-full max-w-md">
            <RadioGroupItem
              icon={DotFilledIcon}
              className="h-9 w-9"
              value="STRIPE"
              id="r2"
            />
            <Label htmlFor="r2" className="w-full">
              <div className="bg-slate-300 rounded-md px-3 py-2 w-full flex justify-center items-center">
                <img
                  className="h-16 w-auto object-contain"
                  src="https://upload.wikimedia.org/wikipedia/commons/thumb/b/ba/Stripe_Logo%2C_revised_2016.svg/375px-Stripe_Logo%2C_revised_2016.svg.png"
                  alt="Stripe"
                />
              </div>
            </Label>
          </div>
        </RadioGroup>
      </div>
      <Button onClick={handleSubmit} className="w-full py-7">
        Submit
      </Button>
    </div>
  );
};

export default Topupform;
