import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import React from "react";
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "@/components/ui/dialog";
import { Button } from "@/components/ui/button";
import PaymentDetailsform from "./PaymentDetailsform";

const PaymentDetails = () => {
  return (
    <div className="px-20">
      <h1 className="text-3xl font-bold py-10">Payment Details</h1>

      {false ? (
        <Card>
          <CardHeader>
            <CardTitle>X Bank</CardTitle>
            <CardDescription>A/C NO : **** **** **** 1221</CardDescription>
          </CardHeader>
          <CardContent>
            <div className="flex items-center">
              <p className="w-32 ">A/C Holder</p>
              <p className="text-gray-400">: Ali Alperen TANIR</p>
            </div>
            <div className="flex items-center">
              <p className="w-32">IFSC</p>
              <p className="text-gray-400">: XB00000000001</p>
            </div>
          </CardContent>
        </Card>
      ) : (
        <Dialog>
          <DialogTrigger>
            <Button className="py-6">Add payment details</Button>
          </DialogTrigger>
          <DialogContent>
            <DialogHeader>
              <DialogTitle>Payment Details</DialogTitle>
            </DialogHeader>
            <PaymentDetailsform />
          </DialogContent>
        </Dialog>
      )}
    </div>
  );
};

export default PaymentDetails;
