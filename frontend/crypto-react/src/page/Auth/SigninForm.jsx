import { Button } from "@/components/ui/button"
import { DialogClose } from "@/components/ui/dialog";
import {
  Form,
  FormControl,
  FormDescription,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from "@/components/ui/form"
import { Input } from "@/components/ui/input"
import React from "react";
import { useForm } from "react-hook-form";

const SigninForm = () => {
  const form = useForm({
    resolver: "",
    defaultValues: {
      email: "",
      password: ""
    },
  });

  const onSubmit = (data) => {
    console.log(data);
  };
  return (
    <div >
        <h1 className="text-xl font-bold  pb-3">Signin</h1>
      <Form {...form}>
        <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-6">

<FormField
            control={form.control}
            name="email"
            render={({ field }) => (
              <FormItem>
                <FormLabel>Email</FormLabel>
                <FormControl>
                  <Input className="border w-full border-gray-700 p-5"placeholder="Enter your email address" {...field} />
                </FormControl>
                <FormMessage />
              </FormItem>
            )}
          />

<FormField
            control={form.control}
            name="password"
            render={({ field }) => (
              <FormItem>
                <FormLabel>Password</FormLabel>
                <FormControl>
                  <Input className="border w-full border-gray-500 p-5"  placeholder="Enter your password" {...field} type="password" />
                </FormControl>
                <FormMessage />
              </FormItem>
            )}
          />
             
          <Button type="submit" className="w-full py-5">
            Sign in
          </Button>
         
        </form>
      </Form>
    </div>
  );
};

export default SigninForm;
