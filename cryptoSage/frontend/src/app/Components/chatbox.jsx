"use client";
import React, { useState } from "react";
import PromptMessage from "./promptMessage";
import ResponseMessage from "./responseMessage";
import axios from "axios";

/*[
    {message:"",role:"user"},
    {message:"response model",role:"model"}
]*/
const Chatbox = () => {
  const [responses, setResponses] = useState([]);

  const [loading, setLoading] = useState(false);

  const handleFetchCoinDetails = async (prompt) => {
    setLoading(true);
    try {
      const { data } = await axios.post("http://localhost:8080/ai/chat", {
        prompt,
      });
      const response = { message: data.message, role: "model" };
      //const userPrompt = {message:prompt, role:"user"}
      setResponses((prev) => [...prev, response]);

      console.log("SUCCESS", data);
    } catch (error) {
      console.log("ERROR", error);
    }
    setLoading(false);
  };
  return (
    <div
      className="chatbox blur-background large-shdow z-50 bg-[#000518] bg-opacity-70 w-[90vw] md:w-[70vw] lg:w-[40vw]
        pb-6 h-[86vh] shadow-2xl shadow-blue-500"
    >
      <div className="h-[13%] pl-3 border-b border-gray-700 flex gap-x-4 items-center">
        <img
          className="rounded-full w-12 h-12"
          src="https://cdn.pixabay.com/photo/2024/02/17/16/08/ai-generated-8579697_640.jpg"
          alt=""
        />

        <div>
          <h1 className="text-lg font-semibold">Crypto Sage </h1>
          <p className="text-sm text-gray-400">Real Time Crypto Market Data</p>
        </div>
      </div>

      <div className="h-[77%]">
        {responses.length ? (
          <div className="flex flex-col py-5 px-5 overflow-y-auto h-full custom-scrollbar">
            {responses.map((item, index) =>
              item.role == "user" ? (
                <div className="self-end" key={index}>
                  <PromptMessage message={item.message} />
                </div>
              ) : (
                <div className="self-start" key={index}>
                  <ResponseMessage message={item.message} />
                </div>
              )
            )}
            {loading && <p>Fetching data from server...</p>}
          </div>
        ) : (
          <div className="p-10 gap-5 h-full flex flex-col justify-center items-center">
            <p className="text-2xl font-bold">Welcome to the Crypto Chat Bot</p>

            <p className="text-gray-500">
              You can ask me anything about Crypto market data
            </p>
          </div>
        )}
      </div>

      <div className="h-[10%] px-5">
        <input
          onKeyPress={(e) => {
            if (e.key === "Enter") {
              const data = { message: e.target.value, role: "user" };
              setResponses((prev) => [...prev, data]);
              handleFetchCoinDetails(e.target.value);
            }
          }}
          type="text"
          className="h-full rounded-full border-gray-700 border bg-transparent px-5 w-full outline-none"
          placeholder="Message CryptoSage "
        />
      </div>
    </div>
  );
};

export default Chatbox;
