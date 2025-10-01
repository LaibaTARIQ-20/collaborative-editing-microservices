const WebSocket = require('ws');
const wss = new WebSocket.Server({ port: 3001 });

let documentContent = "";
let users = [];

console.log('WebSocket server running on ws://localhost:3001');

wss.on('connection', (ws) => {
    console.log('New connection. Total:', wss.clients.size);

    ws.on('message', (message) => {
        try {
            const data = JSON.parse(message);

            if (data.type === 'join') {
                const user = { name: data.username, id: ws };
                users.push(user);

                // Send initial content to new user
                ws.send(JSON.stringify({
                    type: 'init',
                    content: documentContent,
                    users: users.map(u => ({ name: u.name }))
                }));

                // Notify others
                broadcast({
                    type: 'join',
                    username: data.username,
                    users: users.map(u => ({ name: u.name }))
                }, ws);

                console.log(data.username + ' joined');
            }

            else if (data.type === 'update') {
                documentContent = data.content;
                broadcast({
                    type: 'update',
                    content: data.content,
                    username: data.username
                }, ws);
            }

            else if (data.type === 'typing') {
                broadcast({
                    type: 'typing',
                    username: data.username
                }, ws);
            }
        } catch (e) {
            console.error('Error:', e);
        }
    });

    ws.on('close', () => {
        users = users.filter(u => u.id !== ws);
        broadcast({
            type: 'leave',
            users: users.map(u => ({ name: u.name }))
        }, ws);
        console.log('User disconnected. Total:', wss.clients.size);
    });
});

function broadcast(data, sender) {
    wss.clients.forEach(client => {
        if (client !== sender && client.readyState === WebSocket.OPEN) {
            client.send(JSON.stringify(data));
        }
    });
}