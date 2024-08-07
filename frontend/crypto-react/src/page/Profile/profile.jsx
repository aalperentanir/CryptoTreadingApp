import { Badge } from "@/components/ui/badge";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { VerifiedIcon } from "lucide-react";
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
import AccountVerificationform from "./AccountVerificationform";
import { useSelector } from "react-redux";

const Profile = () => {
  const {auth} = useSelector(store=> store)
    const handleEnabledTwoStepVerification =() =>{
        console.log("Your two factor otp enabled")
    }
  return (
    <div className="flex flex-col items-center mb-5">
      <div className="pt-10 w-full lg:w-[60%]">
        <Card>
          <CardHeader className="pb-9">
            <CardTitle>Your Information</CardTitle>
          </CardHeader>
          <CardContent>
            <div className="lg:flex gap-32">
              <div className="space-y-7">
                <div className="flex">
                  <p className="w-[9rem]">Email: </p>
                  <p className="text-gray-400">{auth.user?.email}</p>
                </div>

                <div className="flex">
                  <p className="w-[9rem]">Full Name: </p>
                  <p className="text-gray-400">{auth.user?.fullName}</p>
                </div>

                <div className="flex">
                  <p className="w-[9rem]">Date Of Birth: </p>
                  <p className="text-gray-400">18.01.2001</p>
                </div>

                <div className="flex">
                  <p className="w-[9rem]">Nationality: </p>
                  <p className="text-gray-400">Turkish</p>
                </div>
              </div>

              <div className="space-y-7">
                <div className="flex">
                  <p className="w-[9rem]">Address: </p>
                  <p className="text-gray-400">Adana, Turkey</p>
                </div>

                <div className="flex">
                  <p className="w-[9rem]">City: </p>
                  <p className="text-gray-400">Adana</p>
                </div>

                <div className="flex">
                  <p className="w-[9rem]">Postcode: </p>
                  <p className="text-gray-400">01000</p>
                </div>

                <div className="flex">
                  <p className="w-[9rem]">Country: </p>
                  <p className="text-gray-400">Turkey</p>
                </div>
              </div>
            </div>
          </CardContent>
        </Card>

        <div className="mt-6">
          <Card className="w-full">
            <CardHeader className="pb-7">
              <div className="flex items-center gap-3">
                <CardTitle>2 Step Verification</CardTitle>
                {true ? (
                  <Badge className="bg-green-600 space-x-2 text-white">
                    <VerifiedIcon />
                    <span>Enabled</span>
                  </Badge>
                ) : (
                  <Badge className="bg-red-500">Disable</Badge>
                )}
              </div>
            </CardHeader>

            <CardContent>
              <div>
                <Dialog>
                  <DialogTrigger><Button>Enabled Two Step Verification</Button></DialogTrigger>
                  <DialogContent>
                    <DialogHeader>
                      <DialogTitle>Verify Your Account</DialogTitle>

                    </DialogHeader>
                    <AccountVerificationform handleSubmit={handleEnabledTwoStepVerification}/>
                  </DialogContent>
                </Dialog>
              </div>
            </CardContent>
          </Card>
        </div>
      </div>
    </div>
  );
};

export default Profile;
