import Image from "next/image";
import Chatbox from "./Components/chatbox";

export default function Home() {
  return (
    <main className="flex h-screen flex-col items-center justify-center">

      <Chatbox/>

    </main>
  );
}
