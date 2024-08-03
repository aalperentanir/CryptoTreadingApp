import { Button } from "@/components/ui/button";
import React from "react";
import AssetTable from "./AssetTable";
import StockChart from "./StockChart";
import { Input } from "@/components/ui/input";

import { Avatar, AvatarImage } from "@/components/ui/avatar";
import { Cross1Icon, DotIcon } from "@radix-ui/react-icons";
import { MessageCircle } from "lucide-react";

const Home = () => {
  const [category, setCategory] = React.useState("all");
  const [inputValue, setInputValue] = React.useState("");
  const [isBotRealease, setIsBotRealease] = React.useState(false);

  const handleBotRealease = () => setIsBotRealease(!isBotRealease);

  const handleCategory = (value) => {
    setCategory(value);
  };

  const handleChange = (e) => {
    setInputValue(e.target.value);
  };

  const handleKeyPress = (event) => {
    if (event.key === "Enter") {
      console.log(inputValue);
      setInputValue("");
    }
  };

  return (
    <div className="relative">
      <div className="lg:flex">
        <div className="lg:w-[50%] lg:border-r">
          <div className="p-3 flex items-center gap-4">
            <Button
              onClick={() => handleCategory("all")}
              variant={category === "all" ? "default" : "outline"}
              className="rounded-full"
            >
              All
            </Button>

            <Button
              onClick={() => handleCategory("top50")}
              variant={category === "top50" ? "default" : "outline"}
              className="rounded-full"
            >
              Top 50
            </Button>

            <Button
              onClick={() => handleCategory("topGainers")}
              variant={category === "topGainers" ? "default" : "outline"}
              className="rounded-full"
            >
              Top Gainers
            </Button>

            <Button
              onClick={() => handleCategory("topLosers")}
              variant={category === "topLosers" ? "default" : "outline"}
              className="rounded-full"
            >
              Top Losers
            </Button>
          </div>
          <AssetTable />
        </div>

        <div className="hidden lg:block lg:w-[50%] p-5">
          <StockChart />
          <div className="flex gap-5 items-center">
            <div>
              <Avatar>
                <AvatarImage
                  src={
                    "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1696501400"
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
                <p className="text-xl font-bold">12345</p>
                <p className="text-red-600">
                  <span>-1264896398.33</span>
                  <span>(-0.123465789%)</span>
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
      <section className="absolute bottom-5 right-5 z-40 flex flex-col justify-end items-end gap-2">
        {isBotRealease && (
          <div className="rounded-md w-[20rem] md:w-[25rem] lg:w-[25rem] h-[70vh] bg-slate-100">
            <div className="flex justify-between items-center border-b px-6 h-[12%]">
              <p>Chat Bot</p>
              <Button onClick={handleBotRealease} variant="ghost" size="icon">
                <Cross1Icon />
              </Button>
            </div>

            <div className="h-[76%] flex flex-col overflow-y-auto gap-5 px-5 py-2">
              <div className="self-start pb-5 w-auto">
                <div className="px-5 py-2 rounded-md bg-slate-300 w-auto">
                  <p>
                    Hello, Ali Alperen how can I assist you with the
                    cryptocurrency exchange?
                  </p>
                </div>
              </div>

              {[1, 1, 1, 1].map((item, i) => (
                <div
                  key={i}
                  className={`pb-5 w-auto ${
                    i % 2 === 0 ? "self-start" : "self-end"
                  }`}
                >
                  <div className="px-5 py-2 rounded-md bg-slate-300 w-auto">
                    <p>{i % 2 === 0 ? "Message AI" : "Ali Alperen TANIR"}</p>
                  </div>
                </div>
              ))}
            </div>
            <div className="h-[12%] border-t">
              <Input
                className="w-full h-full border-none outline-none px-4 py-2"
                placeholder="Message AI"
                onChange={handleChange}
                value={inputValue}
                onKeyPress={handleKeyPress}
              />
            </div>
          </div>
        )}
        <div className="relative w-[10rem] cursor-pointer group">
          <Button
            onClick={handleBotRealease}
            className="w-full h-[3rem] gap-2 items-center"
          >
            <MessageCircle
              size={30}
              className="fill-[#ffffff] -rotate-90 stroke-none group-hover:fill-[#ffffff]"
            />
            <span className="text-1xl">Ask To AI</span>
          </Button>
        </div>
      </section>
    </div>
  );
};

export default Home;
